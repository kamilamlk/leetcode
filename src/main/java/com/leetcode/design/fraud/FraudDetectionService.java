package com.leetcode.design.fraud;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class FraudDetectionService {
    private static final long MINUTE_MILLIS = 60_000;
    private static final long HOUR_MILLIS   = 3600_000;
    private static final long MAX_AMOUNT    = 10_000;
    private static final int  MAX_RPM       = 3;

    private final Map<String, Deque<Long>> shortLivingTransactions; // last 1 min
    private final Map<String, Deque<Trx>> longLivingTransactions;   // last 1 hour
    private final Map<String, Long> amounts;                        // rolling 1-hour balance
    private final Map<String, ReentrantLock> locks;                 // per-user locks
    private final Set<String> suspiciousUsers;                      // quick lookup set

    public FraudDetectionService() {
        this.shortLivingTransactions = new ConcurrentHashMap<>();
        this.longLivingTransactions  = new ConcurrentHashMap<>();
        this.amounts                 = new ConcurrentHashMap<>();
        this.locks                   = new ConcurrentHashMap<>();
        this.suspiciousUsers         = ConcurrentHashMap.newKeySet();
    }

    public void recordTransaction(String userId, long amount, long timestampMillis) {
        withLock(locks.computeIfAbsent(userId, k -> new ReentrantLock()), () -> {
            Deque<Long> shortLiving = shortLivingTransactions.computeIfAbsent(userId, k -> new ArrayDeque<>());
            Deque<Trx> longLiving   = longLivingTransactions.computeIfAbsent(userId, k -> new ArrayDeque<>());

            cleanUp(userId, timestampMillis, shortLiving, longLiving);

            Trx trx = new Trx(amount, timestampMillis);

            // add to minute window
            if (trx.time() > timestampMillis - MINUTE_MILLIS) {
                shortLiving.offer(trx.time());
            }

            // add to hour window + amounts
            if (trx.time() > timestampMillis - HOUR_MILLIS) {
                longLiving.offer(trx);
                amounts.compute(userId, (k, v) -> (v == null ? 0L : v) + trx.amount());
            }

            // update suspicious set
            if (isSuspiciousInternal(userId, shortLiving)) {
                suspiciousUsers.add(userId);
            } else {
                suspiciousUsers.remove(userId);
            }
            return null;
        });
    }

    private void cleanUp(String userId, long currentTime, Deque<Long> shortLiving, Deque<Trx> longLiving) {
        while (!shortLiving.isEmpty() && shortLiving.peek() <= (currentTime - MINUTE_MILLIS)) {
            shortLiving.poll();
        }
        while (!longLiving.isEmpty() && longLiving.peek().time() <= (currentTime - HOUR_MILLIS)) {
            Trx old = longLiving.poll();
            amounts.compute(userId, (k, v) -> (v == null ? 0L : v) - old.amount());
        }
    }

    public boolean isSuspicious(String userId) {
        return withLock(locks.computeIfAbsent(userId, k -> new ReentrantLock()), () -> {
            Deque<Long> shortLiving = shortLivingTransactions.computeIfAbsent(userId, k -> new ArrayDeque<>());
            Deque<Trx> longLiving   = longLivingTransactions.computeIfAbsent(userId, k -> new ArrayDeque<>());
            cleanUp(userId, System.currentTimeMillis(), shortLiving, longLiving);
            return isSuspiciousInternal(userId, shortLiving);
        });
    }

    private boolean isSuspiciousInternal(String userId, Deque<Long> shortLiving) {
        long balance = amounts.getOrDefault(userId, 0L);
        return balance > MAX_AMOUNT || shortLiving.size() > MAX_RPM;
    }

    public List<String> getAllSuspiciousUsers() {
        return new ArrayList<>(suspiciousUsers);
    }

    private <R> R withLock(ReentrantLock lock, Supplier<R> supplier) {
        lock.lock();
        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }
}