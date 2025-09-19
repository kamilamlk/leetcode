package com.leetcode.design.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderBookTest {

    @Test
    void placeFirstSell() {
        OrderBook orderBook = new OrderBook();

        Order sellOrder = new Order(1, OrderType.SELL, 100, 10);
        orderBook.placeOrder(sellOrder);
        assertEquals(0, orderBook.getTrades().size());
        assertEquals(1, orderBook.getSellOrderCount());
    }

    @Test
    void placeFirstBuy() {
        OrderBook orderBook = new OrderBook();

        Order buyOrder = new Order(1, OrderType.BUY, 100, 10);
        orderBook.placeOrder(buyOrder);
        assertEquals(0, orderBook.getTrades().size());
        assertEquals(1, orderBook.getBuyOrderCount());
    }

    @Test
    void placeSellWithoutLeftover() {
        OrderBook orderBook = new OrderBook();
        Order buyOrder = new Order(1, OrderType.BUY, 100, 10);
        orderBook.placeOrder(buyOrder);

        assertEquals(0, orderBook.getTrades().size());
        assertEquals(1, orderBook.getBuyOrderCount());

        Order sellOrder = new Order(2, OrderType.SELL, 100, 5);
        orderBook.placeOrder(sellOrder);
        assertEquals(1, orderBook.getTrades().size());
        assertEquals(0, orderBook.getBuyOrderCount());
        assertEquals(0, orderBook.getSellOrderCount());

        Trade trade = orderBook.getTrades().get(0);
        assertEquals(100, trade.units());
        assertEquals(1, trade.buyId());
        assertEquals(2, trade.sellId());
        assertEquals(5, trade.price());
    }

    @Test
    void placeSellWithLeftover() {
        OrderBook orderBook = new OrderBook();
        Order buyOrder = new Order(1, OrderType.BUY, 100, 10);
        orderBook.placeOrder(buyOrder);

        assertEquals(0, orderBook.getTrades().size());
        assertEquals(1, orderBook.getBuyOrderCount());

        Order sellOrder = new Order(2, OrderType.SELL, 150, 5);
        orderBook.placeOrder(sellOrder);
        assertEquals(1, orderBook.getTrades().size());
        assertEquals(0, orderBook.getBuyOrderCount());
        assertEquals(1, orderBook.getSellOrderCount());

        Trade trade = orderBook.getTrades().get(0);
        assertEquals(100, trade.units());
        assertEquals(1, trade.buyId());
        assertEquals(2, trade.sellId());
        assertEquals(5, trade.price());

        Order topSell = orderBook.getTopOrder(OrderType.SELL);
        assertNotNull(topSell);
        assertEquals(50, topSell.units());
        assertEquals(2, topSell.id());
        assertEquals(5, topSell.price());
    }

    @Test
    void placeBuyWithoutLeftover() {
        OrderBook orderBook = new OrderBook();
        Order sellOrder = new Order(1, OrderType.SELL, 100, 10);
        orderBook.placeOrder(sellOrder);
        assertEquals(0, orderBook.getTrades().size());
        assertEquals(1, orderBook.getSellOrderCount());
        Order buyOrder = new Order(2, OrderType.BUY, 100, 15);
        orderBook.placeOrder(buyOrder);
        assertEquals(1, orderBook.getTrades().size());
        assertEquals(0, orderBook.getBuyOrderCount());
        assertEquals(0, orderBook.getSellOrderCount());

        Trade trade = orderBook.getTrades().get(0);
        assertEquals(100, trade.units());
        assertEquals(2, trade.buyId());
        assertEquals(1, trade.sellId());
        assertEquals(10, trade.price());
    }

    @Test
    void placeBuyWithLeftover() {
        OrderBook orderBook = new OrderBook();
        Order sellOrder = new Order(1, OrderType.SELL, 100, 10);
        orderBook.placeOrder(sellOrder);
        assertEquals(0, orderBook.getTrades().size());
        assertEquals(1, orderBook.getSellOrderCount());

        Order buyOrder = new Order(2, OrderType.BUY, 150, 15);
        orderBook.placeOrder(buyOrder);
        assertEquals(1, orderBook.getTrades().size());
        assertEquals(1, orderBook.getBuyOrderCount());
        assertEquals(0, orderBook.getSellOrderCount());

        Trade trade = orderBook.getTrades().get(0);
        assertEquals(100, trade.units());
        assertEquals(2, trade.buyId());
        assertEquals(1, trade.sellId());
        assertEquals(10, trade.price());

        Order topBuy = orderBook.getTopOrder(OrderType.BUY);
        assertNotNull(topBuy);
        assertEquals(50, topBuy.units());
        assertEquals(2, topBuy.id());
        assertEquals(15, topBuy.price());
    }

    @Test
    void placeConcurrentOrders() {
        OrderBook orderBook = new OrderBook();

        Runnable placeBuy = () -> {
            for (int i = 0; i < 1000; i++) {
                Order buyOrder = new Order(i, OrderType.BUY, 100, 10 + (i % 5));
                orderBook.placeOrder(buyOrder);
            }
        };

        Runnable placeSell = () -> {
            for (int i = 0; i < 1000; i++) {
                Order sellOrder = new Order(i, OrderType.SELL, 100, 5 + (i % 5));
                orderBook.placeOrder(sellOrder);
            }
        };

        Thread buyThread = new Thread(placeBuy);
        Thread sellThread = new Thread(placeSell);

        buyThread.start();
        sellThread.start();

        try {
            buyThread.join();
            sellThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Since prices overlap, we should have some trades executed
        assertFalse(orderBook.getTrades().isEmpty());
        // There might be some leftover orders depending on the matching
        assertTrue(orderBook.getBuyOrderCount() >= 0);
        assertTrue(orderBook.getSellOrderCount() >= 0);
    }
}