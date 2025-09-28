package com.leetcode.design.rate.limiter;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class SingleRateLimiterTest {
    @Test
    void shouldLimit() throws InterruptedException {
        SingleRateLimiter limiter = new SingleRateLimiter(3);
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger successCounter = new AtomicInteger();
        AtomicInteger failCounter = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    latch.await();
                    if (limiter.callApi("user")) {
                        successCounter.incrementAndGet();
                    } else {
                        failCounter.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
        }
        latch.countDown();
        executor.shutdown();
        executor.awaitTermination(2000L, TimeUnit.MILLISECONDS);
        boolean done = executor.isShutdown();
        Assertions.assertThat(done).isTrue();
        Assertions.assertThat(successCounter.get()).isEqualTo(3);
        Assertions.assertThat(failCounter.get()).isEqualTo(7);
    }
}