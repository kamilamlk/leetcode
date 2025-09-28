package com.leetcode.design.rate.limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SingleRateLimiter {
    private final int limit;
    private final Map<String, Semaphore> semaphores;

    public SingleRateLimiter(final int limit) {
        this.limit = limit;
        this.semaphores = new ConcurrentHashMap<>();
    }

    public boolean callApi(String userId) {
        Semaphore semaphore = semaphores.computeIfAbsent(userId, k -> new Semaphore(limit));
        boolean acquired = false;
        try {
            acquired = semaphore.tryAcquire(100, TimeUnit.MILLISECONDS);
            if (acquired) {
                Thread.sleep(1000);
                return true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (acquired) {
                semaphore.release(1);
            }
        }
        return false;
    }
}
