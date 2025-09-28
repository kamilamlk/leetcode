package com.leetcode.design.rate.limiter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class TokenBucketRateLimiterTest {
    @Test
    void shouldRefill() {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 5);
        for (int i = 0; i < 6; i++) {
            limiter.callApi("user");
        }
        Assertions.assertThat(limiter.getAvailableTokens("user"))
            .isEqualTo(0);
    }

    @Test
    void shouldRefillConcurrent() throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 5);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger consumed = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            executor.submit(() -> {
                if (limiter.callApi("user")) {
                    consumed.incrementAndGet();
                }
                latch.countDown();
            });
        }
        latch.await();
        Assertions.assertThat(limiter.getAvailableTokens("user"))
            .isZero();
        Assertions.assertThat(consumed.get()).isEqualTo(5);

        Thread.sleep(1000);

        consumed.set(0);
        CountDownLatch latch2 = new CountDownLatch(6);
        Assertions.assertThat(
            limiter.getAvailableTokens("user")
        ).isZero();

        for (int i = 0; i < 6; i++) {
            executor.submit(() -> {
                if (limiter.callApi("user")) {
                    consumed.incrementAndGet();
                }
                latch2.countDown();
            });
        }
        latch2.await();
        Assertions.assertThat(limiter.getAvailableTokens("user"))
            .isZero();
        Assertions.assertThat(consumed.get()).isEqualTo(5);

        executor.shutdown();
        Assertions.assertThat(executor.awaitTermination(3, TimeUnit.SECONDS))
            .isTrue();
    }
}