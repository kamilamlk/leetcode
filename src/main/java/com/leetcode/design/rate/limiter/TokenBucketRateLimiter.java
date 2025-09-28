package com.leetcode.design.rate.limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {
    private final int capacity; // max tokens allowed
    private final int refillRate; // tokens per second;
    Map<String, Bucket> buckets;

    public TokenBucketRateLimiter(final int capacity,
                                  final int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.buckets = new ConcurrentHashMap<>();
    }

    public boolean callApi(String userId) {
        Bucket bucket = buckets.computeIfAbsent(userId, k -> new Bucket(capacity, refillRate));
        return bucket.tryConsume();
    }

    public int getAvailableTokens(String userId) {
        return buckets
            .computeIfAbsent(userId, k -> new Bucket(capacity, refillRate))
            .tokens;
    }

    private static class Bucket {
        private final int capacity;
        private final int refillRate;
        private int tokens;
        private long lastRefillTime;

        public Bucket(final int capacity, final int refillRate) {
            this.capacity = capacity;
            this.refillRate = refillRate;
            this.tokens = capacity;
            this.lastRefillTime = System.currentTimeMillis();
        }

        synchronized boolean tryConsume() {
            refill();
            if (tokens >= 1) {
                tokens--;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.nanoTime();
            long seconds = (now - lastRefillTime) / 1_000_000_000;
            int tokensToAdd = (int)(seconds * refillRate);
            if (tokensToAdd > 0) {
                tokens = Math.min(capacity, tokens + tokensToAdd);
                lastRefillTime = now;
            }
        }
    }
}
