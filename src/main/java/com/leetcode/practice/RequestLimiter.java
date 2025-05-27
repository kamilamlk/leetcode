package com.leetcode.practice;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class RequestLimiter<K> {
    private final long blockingPeriod;
    private final int maxFailedRequests;
    private final ConcurrentHashMap<K, LinkedBlockingDeque<Long>> failedRequests;

    public RequestLimiter(long blockingPeriod, int maxFailedRequests) {
        this.blockingPeriod = blockingPeriod;
        this.maxFailedRequests = maxFailedRequests;
        this.failedRequests = new ConcurrentHashMap<>();
    }

    boolean canProcess(K key) {
        var requests = failedRequests.get(key);
        if (requests == null) {
            return true;
        }
        synchronized (requests) {
            var currentTime = System.currentTimeMillis();
            // обнулили счетчик если время блокировки исчерпано
            removeExpiredAttempts(requests, currentTime);
            // ничего не делать если все равно превысили лимит
            return requests.size() < maxFailedRequests;
        }
    }

    void registerFailure(K key) {
        var requests = failedRequests.computeIfAbsent(key, k -> new LinkedBlockingDeque<>());
        synchronized (requests) {
            var currentTime = System.currentTimeMillis();
            // обнулили счетчик
            removeExpiredAttempts(requests, currentTime);
            // ничего не делать если все равно превысили лимит
            if (requests.size() >= maxFailedRequests) {
                return;
            }
            requests.offer(currentTime);
        }
    }

    void registerSuccess(K key) {
        failedRequests.remove(key);
    }

    private void removeExpiredAttempts(LinkedBlockingDeque<Long> requests, long time) {
        if (requests.size() >= maxFailedRequests && time - requests.peekLast() > blockingPeriod) {
            requests.clear();
        }
    }
}
