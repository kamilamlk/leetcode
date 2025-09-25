package com.leetcode.design.rate.limiter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

class RateLimiterTest {

    @Test
    void shouldAllowFirstRequests() {
        RateLimiter rateLimiter = new RateLimiter(5, 5000); // 5 per 5 seconds
        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(rateLimiter.allowRequest("user1"))
                .isTrue();
        }
    }

    @Test
    void shouldNotAllowByMaxRequests() {
        RateLimiter rateLimiter = new RateLimiter(5, 5000); // 5 per 5 seconds
        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(rateLimiter.allowRequest("user1"))
                .isTrue();
        }
        Assertions.assertThat(rateLimiter.allowRequest("user1"))
            .isFalse();
    }

    @Test
    void shouldEvictOldRequests() {
        RateLimiter rateLimiter = new RateLimiter(5, 550); // 5 per 5 seconds
        for (int i = 0; i < 5; i++) {
            await().atMost(110, TimeUnit.MILLISECONDS)
                .until(() -> rateLimiter.allowRequest("user1"));
        }
        await().atLeast(110, TimeUnit.MILLISECONDS)
            .until(() -> rateLimiter.allowRequest("user1"));
    }

    @Test
    void shouldHandleConcurrent() {

    }
}