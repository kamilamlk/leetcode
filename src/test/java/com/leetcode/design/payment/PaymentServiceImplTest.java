package com.leetcode.design.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {

    @Test
    void deposit() {
        PaymentService paymentService = new PaymentServiceImpl();
        assertEquals(0, paymentService.getBalance("user1"));
        assertTrue(paymentService.deposit("user1", 100));
        assertEquals(100, paymentService.getBalance("user1"));
    }

    @Test
    void concurrentDeposit() throws InterruptedException {
        PaymentService paymentService = new PaymentServiceImpl();
        assertEquals(0, paymentService.getBalance("user1"));
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService es = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            es.submit(() -> {
                try {
                    latch.await();
                    paymentService.deposit("user1", 100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        es.shutdown();
        latch.countDown();

        boolean completed = es.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(completed);
        assertEquals(2000, paymentService.getBalance("user1"));
        assertEquals(20, paymentService.getUserTransactions("user1").size());
    }

    @Test
    void withdraw() {
        PaymentService paymentService = new PaymentServiceImpl();
        assertEquals(0, paymentService.getBalance("user1"));
        assertFalse(paymentService.withdraw("user1", 100));
        assertEquals(0, paymentService.getBalance("user1"));

        assertTrue(paymentService.deposit("user1", 100));
        assertEquals(100, paymentService.getBalance("user1"));
        assertTrue(paymentService.withdraw("user1", 100));
        assertEquals(0, paymentService.getBalance("user1"));
    }

    @Test
    void concurrentWithdraw() throws InterruptedException {
        PaymentService paymentService = new PaymentServiceImpl();
        assertEquals(0, paymentService.getBalance("user1"));
        paymentService.deposit("user1", 1000);
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.submit(() -> {
                try {
                    latch.await();
                    paymentService.withdraw("user1", 100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        es.shutdown();
        latch.countDown();

        boolean completed = es.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(completed);
        assertEquals(0, paymentService.getBalance("user1"));
        assertEquals(11, paymentService.getUserTransactions("user1").size());
    }


    @Test
    void transfer() {
        PaymentService paymentService = new PaymentServiceImpl();
        paymentService.deposit("user1", 1000);
        paymentService.deposit("user2", 1000);
        String trxId = paymentService.transfer("user1", "user2", 500);
        assertNotNull(trxId);

        assertEquals(2, paymentService.getUserTransactions("user1").size());
        assertEquals(2, paymentService.getUserTransactions("user2").size());
        assertEquals(500, paymentService.getBalance("user1"));
        assertEquals(1500, paymentService.getBalance("user2"));
        Transaction trx = paymentService.getTransaction(trxId);
        assertNotNull(trx);
        assertEquals(trxId, trx.id());
        assertEquals("user1", trx.fromUser());
        assertEquals("user2", trx.toUser());
        assertEquals(500, trx.amount());

        Assertions.assertThat(paymentService.getUserTransactions("user1"))
            .contains(trx);
        Assertions.assertThat(paymentService.getUserTransactions("user2"))
            .contains(trx);
    }

    @Test
    void concurrentTransfer() throws InterruptedException {
        PaymentService paymentService = new PaymentServiceImpl();
        paymentService.deposit("user1", 1000);
        paymentService.deposit("user2", 1000);
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService es = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            int index = i;
            es.submit(
                () -> {
                    try {
                        latch.await();
                        if (index % 2 == 0) {
                            paymentService.transfer("user1", "user2", 100);
                        } else {
                            paymentService.transfer("user2", "user1", 100);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            );
        }
        es.shutdown();
        latch.countDown();
        boolean completed = es.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(completed);
        assertEquals(1000, paymentService.getBalance("user1"));
        assertEquals(1000, paymentService.getBalance("user2"));
        assertEquals(11, paymentService.getUserTransactions("user1").size());
        assertEquals(11, paymentService.getUserTransactions("user2").size());
    }
}