package com.leetcode.ocp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1. Thread-Safe Wallet (Bank Account System)
 * 	•	Implement a class WalletService with methods:
 * 	•	deposit(String accountId, long amount)
 * 	•	withdraw(String accountId, long amount)
 * 	•	transfer(String from, String to, long amount)
 * Requirements:
 * 	•	Thread-safe under concurrent deposits/transfers.
 * 	•	Prevent overdrafts.
 * 	•	Prevent deadlocks (hint: lock ordering).
 * 	•	Test with ExecutorService running 1000+ random operations.
 *
 * 💡 Skills: synchronization, locks, deadlock prevention, correctness under load.
 */
public class Wallet {
    Map<String, Long> wallets;

    public Wallet() {
        this.wallets = new HashMap<>();
    }

    /**
     * Issues: 1. Not thread-safe: Concurrent access to the wallets map can case data inconsistency.
     * @param accountId
     * @param amount
     */
    public void deposit(String accountId, long amount) {
        wallets.put(accountId, wallets.getOrDefault(accountId, 0L) + amount);
    }

    public void withdraw(String accountId, long amount) {
        if (wallets.get(accountId) < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        wallets.put(accountId, wallets.get(accountId) - amount);
    }

    public void transfer(String from, String to, long amount) {
        withdraw(from, amount);
        deposit(to, amount);
    }

    public long getBalance(String accountId) {
        return wallets.getOrDefault(accountId, 0L);
    }

    public static void main(String[] args) throws InterruptedException {
        Wallet wallet = new Wallet();
        CountDownLatch latch = new CountDownLatch(10);
        Runnable[] tasks = new Runnable[10];
        var executors = Executors.newFixedThreadPool(10);
        wallet.deposit("A", 10);
        wallet.deposit("B", 10);
        try {
            for (int i = 0; i < tasks.length; i++) {
                if (i % 2 == 0) {
                    tasks[i] = () -> {
                        latch.countDown();
                        wallet.transfer("A", "B", 10);
                        System.out.println("A: " + wallet.getBalance("A") + ", B: " + wallet.getBalance("B"));
                    };
                } else {
                    tasks[i] = () -> {
                        latch.countDown();
                        wallet.transfer("B", "A", 10);
                        System.out.println("A: " + wallet.getBalance("A") + ", B: " + wallet.getBalance("B"));
                    };
                }

            }
            for (var task : tasks) {
                executors.submit(task);
            }
            latch.await(10, TimeUnit.SECONDS);
        } finally {
            executors.shutdown();
            if (!executors.awaitTermination(5, TimeUnit.SECONDS)) {
                executors.shutdownNow();
            }
        }

        System.out.println("Итоговый баланс:");
        System.out.println("A: " + wallet.getBalance("A") + ", B: " + wallet.getBalance("B"));
    }
}
