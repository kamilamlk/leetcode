package com.leetcode.design.currency.cache;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConversionCache {
    long expiryWindow;
    Map<String, Rate> cache;
    Map<String, ReentrantLock> locks;
    Queue<Rate> expiryQueue;

    public ConversionCache(final long expiryWindow) {
        cache = new ConcurrentHashMap<>();
        locks = new ConcurrentHashMap<>();
        expiryQueue = new PriorityQueue<>(Comparator.comparing(r -> r.timeStamp));
        this.expiryWindow = expiryWindow;
    }

    public void updateRate(String from,
                           String to,
                           double rate,
                           long timestampMillis) {
        String key = from + "_" + to;
        cleanUp();
        withLock(
            locks.computeIfAbsent(key, k -> new ReentrantLock()),
            () -> {
                Rate rateInfo = cache.get(key);
                if (rateInfo == null) { // expired or null
                    rateInfo = new Rate(from, to);
                    rateInfo.timeStamp = timestampMillis;
                    rateInfo.rate = rate;
                    expiryQueue.offer(rateInfo);
                    cache.put(key, rateInfo);
                } else {
                    rateInfo.timeStamp = timestampMillis;
                    rateInfo.rate = rate;
                }
                return null;
            }
        );
    }

    /**
     *
     * @param from
     * @param to
     * @return cached rate if fresh, otherwise null
     */
    Double getRate(String from, String to) {
        String key = from + "_" + to;
        cleanUp();
        Rate rate = cache.get(key);
        if (rate == null) return null;
        return rate.rate;
    }

    /**
     *
     * @return only non-expired rates.
     */
    Map<String, Double> getAllFreshRates() {
        return cache.values().stream()
            .collect(Collectors.toMap(
                rate -> rate.from + "_" + rate.to,
                rate -> rate.rate
            ));
    }

    private void cleanUp() {
        long expiry = System.currentTimeMillis() - expiryWindow;
        while (!expiryQueue.isEmpty() && expiryQueue.peek().timeStamp < expiry) {
            Rate old = expiryQueue.poll();
            String key = old.from + "_" + old.to;
            cache.remove(key);
        }
    }

    private class Rate {
        String from;
        String to;
        Double rate;
        long timeStamp;

        public Rate(final String from, final String to) {
            this.from = from;
            this.to = to;
        }
    }

    private <R> R withLock(ReentrantLock lock, Supplier<R> supplier) {
        try {
            lock.lock();
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }
}
