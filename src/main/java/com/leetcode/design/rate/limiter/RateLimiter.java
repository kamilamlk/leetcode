package com.leetcode.design.rate.limiter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiter {
    Map<String, Deque<Long>> requests;
    Map<String, ReentrantLock> locks;
    int maxRequests;
    int timeWindow;

    public RateLimiter(int maxRequests, int timeWindow) {
        this.maxRequests = maxRequests;
        this.timeWindow = timeWindow;
        this.locks = new ConcurrentHashMap<>();
        this.requests = new ConcurrentHashMap<>();
    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        long limit = currentTime - timeWindow;

        ReentrantLock lock = locks.computeIfAbsent(userId, k -> new ReentrantLock());

        try {
            lock.lock();
            Deque<Long> running = requests
                .computeIfAbsent(userId, k -> new LinkedList<>());
            while (!running.isEmpty() && limit >= running.peek()) {
                running.poll();
            }
            if (running.size() >= maxRequests) return false;

            running.offer(currentTime);
            return true;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
