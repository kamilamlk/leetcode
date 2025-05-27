package com.leetcode.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class RequestLimiterTest {
    @Test
    void shouldProcessRequestsTillMaxFailed() {
        RequestLimiter<String> limiter = new RequestLimiter<>(5000, 5);
        String key = "key";
        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(limiter.canProcess(key));
            limiter.registerFailure(key);
        }
        Assertions.assertFalse(limiter.canProcess(key));
    }

    @Test
    void shouldInvalidateAfterSuccess() {
        RequestLimiter<String> limiter = new RequestLimiter<>(5000, 5);
        String key = "key";
        for (int i = 0; i < 4; i++) {
            Assertions.assertTrue(limiter.canProcess(key));
            limiter.registerFailure(key);
        }
        limiter.registerSuccess(key);
        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(limiter.canProcess(key));
            limiter.registerFailure(key);
        }
        Assertions.assertFalse(limiter.canProcess(key));
    }

    @Test
    void shouldRemoveExpired() {
        int blockingPeriod = 1000;
        RequestLimiter<String> limiter = new RequestLimiter<>(blockingPeriod, 5);
        String key = "key";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(limiter.canProcess(key));
            limiter.registerFailure(key);
        }

        while (System.currentTimeMillis() - start < blockingPeriod + 100){
        }

        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(limiter.canProcess(key));
            limiter.registerFailure(key);
        }
        Assertions.assertFalse(limiter.canProcess(key));
    }

    @Test
    void shouldProcessRequestsTillMaxFailedConcurrently() throws InterruptedException {
        RequestLimiter<String> limiter = new RequestLimiter<>(5000, 5);
        String key = "key";
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    Assertions.assertTrue(limiter.canProcess(key));
                    limiter.registerFailure(key);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        latch.countDown();
        executorService.shutdown();
        if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
            throw new InterruptedException();
        }
        executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                Assertions.assertFalse(limiter.canProcess(key));
                limiter.registerFailure(key);
            });
        }
        executorService.shutdown();
        if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
            throw new InterruptedException();
        }
    }
}