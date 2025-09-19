package com.leetcode.design.wallet;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class WalletServiceImplTest {

    @Test
    void deposit() {
        WalletService walletService = new WalletServiceImpl();

        // check empty balance
        assertEquals(0, walletService.getBalance("user1"));
        // deposit 100
        walletService.deposit("user1", 100);
        assertEquals(100, walletService.getBalance("user1"));
    }

    @Test
    void concurrentDeposit() throws InterruptedException {
        WalletService walletService = new WalletServiceImpl();
        CountDownLatch latch = new CountDownLatch(1);
        Runnable[] tasks = new Runnable[10];
        for (int i = 0; i < 10; i++) {
            tasks[i] = () ->  {
                try {
                    latch.await();
                    walletService.deposit("user1", 100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (Runnable task : tasks) {
            executorService.submit(task);
        }
        latch.countDown();
        executorService.shutdown();
        boolean completed = executorService.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(completed);
        assertEquals(1000, walletService.getBalance("user1"));
    }

    @Test
    void withdrawInsufficient() {
        WalletService walletService = new WalletServiceImpl();

        assertEquals(0, walletService.getBalance("user1"));
        assertFalse(walletService.withdraw("user1", 100));
    }

    @Test
    void withdrawSufficient() {
        WalletService walletService = new WalletServiceImpl();

        assertEquals(0, walletService.getBalance("user1"));
        walletService.deposit("user1", 200);
        assertTrue(walletService.withdraw("user1", 100));
        assertEquals(100, walletService.getBalance("user1"));
    }

    @Test
    void concurrentWithdraw() throws InterruptedException {
        WalletService walletService = new WalletServiceImpl();
        walletService.deposit("user1", 1000);
        CountDownLatch latch = new CountDownLatch(1);
        Runnable[] tasks = new Runnable[10];
        for (int i = 0; i < 10; i++) {
            tasks[i] = () ->  {
                try {
                    latch.await();
                    walletService.withdraw("user1", 100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (Runnable task : tasks) {
            executorService.submit(task);
        }
        latch.countDown();
        executorService.shutdown();
        boolean completed = executorService.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(completed);
        assertEquals(0, walletService.getBalance("user1"));
    }

    @Test
    void transfer() {
        WalletService walletService = new WalletServiceImpl();

        walletService.deposit("user1", 200);
        assertTrue(walletService.transfer("user1", "user2", 100));
        assertEquals(100, walletService.getBalance("user1"));
        assertEquals(100, walletService.getBalance("user2"));

        assertFalse(walletService.transfer("user1", "user2", 200));
        assertEquals(100, walletService.getBalance("user1"));
        assertEquals(100, walletService.getBalance("user2"));
    }

    @Test
    void concurrentTransfer() throws InterruptedException {
        WalletService ws = new WalletServiceImpl();
        ws.deposit("user1", 1000);
        ws.deposit("user2", 1000);
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int index = i;
            Runnable task = () ->  {
                try {
                    latch.await();
                    if (index % 2 == 0) {
                        ws.transfer("user1", "user2", 100);
                    } else {
                        ws.transfer("user2", "user1", 100);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            executorService.submit(task);
        }

        latch.countDown();
        executorService.shutdown();
        boolean completed = executorService.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(completed);
        assertEquals(1000, ws.getBalance("user1"));
        assertEquals(1000, ws.getBalance("user2"));
    }

    @Test
    void concurrentTransferWithMultipleUsers() {
        WalletService walletService = new WalletServiceImpl();
        List<String> users = List.of("user1", "user2", "user3", "user4", "user5");
        for (String user : users) {
            walletService.deposit(user, 1000);
        }
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            int index = i;
            Runnable task = () ->  {
                try {
                    latch.await();
                    // ring transfer
                    String fromUser = users.get(index % users.size());
                    String toUser = users.get((index + 1) % users.size());
                    walletService.transfer(fromUser, toUser, 100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            executorService.submit(task);
        }

        latch.countDown();
        executorService.shutdown();
        try {
            boolean completed = executorService.awaitTermination(10, TimeUnit.SECONDS);
            assertTrue(completed);
            int totalBalance = 0;
            for (String user : users) {
                totalBalance += walletService.getBalance(user);
            }
            assertEquals(5000, totalBalance);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}