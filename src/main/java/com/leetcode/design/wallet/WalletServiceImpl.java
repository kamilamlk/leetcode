package com.leetcode.design.wallet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class WalletServiceImpl implements WalletService {

    Map<String, Integer> userBalances;
    Map<String, ReentrantLock> userLocks;

    public WalletServiceImpl() {
        this.userBalances = new ConcurrentHashMap<>();
        this.userLocks = new ConcurrentHashMap<>();
    }

    @Override
    public boolean deposit(final String userId,
                        final int amount) {
        return withLock(
            userLocks.computeIfAbsent(userId, k -> new ReentrantLock()),
            () -> {
                userBalances.put(userId, userBalances.getOrDefault(userId, 0) + amount);
                return true;
            }
        );
    }

    @Override
    public boolean withdraw(final String userId,
                            final int amount) {
        return withLock(
            userLocks.computeIfAbsent(userId, k -> new ReentrantLock()),
            () -> {
                if (userBalances.getOrDefault(userId, 0) >= amount) {
                    userBalances.put(userId, userBalances.get(userId) - amount);
                    return true;
                }
                return false;
            }
        );
    }

    @Override
    public boolean transfer(final String fromUser,
                            final String toUser,
                            final int amount) {
        userLocks.computeIfAbsent(fromUser, k -> new ReentrantLock());
        userLocks.computeIfAbsent(toUser, k -> new ReentrantLock());

        ReentrantLock firstLock = fromUser.compareTo(toUser) < 0 ? userLocks.get(fromUser) : userLocks.get(toUser);
        ReentrantLock secondLock = fromUser.compareTo(toUser) < 0 ? userLocks.get(toUser) : userLocks.get(fromUser);
        return withLocks(
            firstLock,
            secondLock,
            () -> {
                if (userBalances.getOrDefault(fromUser, 0) >= amount) {
                    userBalances.put(fromUser, userBalances.get(fromUser) - amount);
                    userBalances.put(toUser, userBalances.getOrDefault(toUser, 0) + amount);
                    return true;
                }
                return false;
            }
        );
    }

    @Override
    public int getBalance(final String userId) {
        return userBalances.getOrDefault(userId, 0);
    }

    private static boolean withLocks(ReentrantLock lock1, ReentrantLock lock2, BooleanSupplier supplier) {
        try {
            if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                if (lock2.tryLock(1,TimeUnit.SECONDS)) {
                    return supplier.getAsBoolean();
                } else {
                    lock1.unlock();
                }
            }
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException(e);
        } finally {
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
        }
    }

    private static boolean withLock(ReentrantLock lock, BooleanSupplier supplier) {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                return supplier.getAsBoolean();
            }
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException(e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
