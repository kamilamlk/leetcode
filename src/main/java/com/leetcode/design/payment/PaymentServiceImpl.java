package com.leetcode.design.payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class PaymentServiceImpl implements PaymentService {
    Map<String, Integer> userBalances;
    Map<String, List<Transaction>> userTransactions;
    Map<String, Transaction> transactions;
    Map<String, ReentrantLock> userLocks;

    public PaymentServiceImpl() {
        this.userBalances = new ConcurrentHashMap<>();
        this.userTransactions = new ConcurrentHashMap<>();
        this.transactions = new ConcurrentHashMap<>();

        this.userLocks = new ConcurrentHashMap<>();
    }

    @Override
    public boolean deposit(final String userId, final int amount) {
        if (amount <= 0) {
            return false;
        }
        return withLock(
            userLocks.computeIfAbsent(userId, k -> new ReentrantLock()),
            () -> {
                userBalances.put(userId, userBalances.getOrDefault(userId, 0) + amount);
                Transaction tx = Transaction.of(null, userId, amount);

                transactions.put(tx.id(), tx);
                userTransactions.computeIfAbsent(userId, k -> new ArrayList<>()).add(tx);
                return true;
            }
        );
    }

    @Override
    public boolean withdraw(final String userId, final int amount) {
        if (amount <= 0) {
            return false;
        }
        return withLock(
            userLocks.computeIfAbsent(userId, k -> new ReentrantLock()),
            () -> {
                if (userBalances.getOrDefault(userId, 0) < amount) {
                    return false;
                }
                userBalances.put(userId, userBalances.get(userId) - amount);
                Transaction tx = Transaction.of(userId, null, amount);

                transactions.put(tx.id(), tx);
                userTransactions.computeIfAbsent(userId, k -> new ArrayList<>()).add(tx);
                return true;
            }
        );
    }

    @Override
    public String transfer(final String fromUser,
                            final String toUser,
                            final int amount) {
        if (amount < 0 || fromUser.equals(toUser)) {
            return null;
        }

        ReentrantLock lock1 = fromUser.compareTo(toUser) < 0
                        ? userLocks.computeIfAbsent(fromUser, k -> new ReentrantLock())
                        : userLocks.computeIfAbsent(toUser, k -> new ReentrantLock());
        ReentrantLock lock2 = fromUser.compareTo(toUser) < 0
                        ? userLocks.computeIfAbsent(toUser, k -> new ReentrantLock())
                        : userLocks.computeIfAbsent(fromUser, k -> new ReentrantLock());

        return withLocks(
            lock1, lock2,
            () -> {
                if (userBalances.getOrDefault(fromUser, 0) < amount) {
                    return null;
                }
                // In real world rollback is needed if any of the following operations fail
                userBalances.put(fromUser, userBalances.get(fromUser) - amount);
                userBalances.put(toUser, userBalances.getOrDefault(toUser, 0) + amount);
                Transaction tx = Transaction.of(fromUser, toUser, amount);
                transactions.put(tx.id(), tx);
                userTransactions.computeIfAbsent(fromUser, k -> new ArrayList<>()).add(tx);
                userTransactions.computeIfAbsent(toUser, k -> new ArrayList<>()).add(tx);
                return tx.id();
            }
        );
    }

    @Override
    public int getBalance(final String userId) {
        return userBalances.getOrDefault(userId, 0);
    }

    @Override
    public Transaction getTransaction(final String txId) {
        return transactions.get(txId);
    }

    @Override
    public List<Transaction> getUserTransactions(final String userId) {
        return Collections.unmodifiableList(userTransactions.getOrDefault(userId, new ArrayList<>()));
    }

    private boolean withLock(ReentrantLock lock, BooleanSupplier supplier) {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                return supplier.getAsBoolean();
            }
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    private <R> R withLocks(ReentrantLock lock1, ReentrantLock lock2, Supplier<R> supplier) {
        try {
            if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                    return supplier.get();
                }
            }
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
        }
    }
}
