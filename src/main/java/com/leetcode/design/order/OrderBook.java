package com.leetcode.design.order;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderBook {
    Queue<Order> buyOrders;
    Queue<Order> sellOrders;

    ConcurrentLinkedQueue<Trade> trades;
    Lock tradeLock;

    public OrderBook() {
        this.buyOrders = new PriorityBlockingQueue<>(
            11,
            Comparator.comparingInt(Order::price).reversed()
        );
        this.sellOrders = new PriorityBlockingQueue<>(
            11,
            Comparator.comparingInt(Order::price)
        );
        this.trades = new ConcurrentLinkedQueue<>();
        this.tradeLock = new ReentrantLock();
    }

    public void placeOrder(Order order) {
        if (order.type() == OrderType.BUY) {
            placeBuy(order);
        } else {
            placeSell(order);
        }
    }

    public void placeBuy(Order buy) {
        int remainingUnits = buy.units();
        while (!sellOrders.isEmpty() && remainingUnits > 0) {
            Order sell = sellOrders.poll();
            synchronized (sell) {
                if (sell.price() > buy.price()) {
                    break;
                }
                int tradeUnits = Math.min(remainingUnits, sell.units());
                remainingUnits -= tradeUnits;
                trades.add(Trade.of(buy, sell, tradeUnits));

                if (sell.units() > tradeUnits) {
                    sellOrders.offer(sell.leftover(sell.units() - tradeUnits));
                }
            }
        }

        if (remainingUnits > 0) {
            buyOrders.offer(buy.leftover(remainingUnits));
        }
    }

    public void placeSell(Order sell) {
        int remainingUnits = sell.units();
        while (!buyOrders.isEmpty() && remainingUnits > 0) {
            Order buy = buyOrders.poll();
            synchronized (buy) {
                if (buy.price() < sell.price()) {
                    break;
                }
                int tradeUnits = Math.min(remainingUnits, buy.units());
                remainingUnits -= tradeUnits;
                trades.add(Trade.of(buy, sell, tradeUnits));
                if (buy.units() > tradeUnits) {
                    buyOrders.offer(buy.leftover(buy.units() - tradeUnits));
                }
            }
        }

        if (remainingUnits > 0) {
            sellOrders.offer(sell.leftover(remainingUnits));
        }
    }

    public List<Trade> getTrades() {
        return trades.stream().toList();
    }

    public int getBuyOrderCount() {
        return buyOrders.size();
    }

    public int getSellOrderCount() {
        return sellOrders.size();
    }

    public Order getTopOrder(OrderType type) {
        if (type == OrderType.BUY) {
            return buyOrders.peek();
        } else {
            return sellOrders.peek();
        }
    }
}
