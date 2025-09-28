package com.leetcode.design.logger;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class LoggerTest {
    @Test
    void shouldLogInSingleThread() throws InterruptedException {
        List<String> result = new ArrayList<>();
        ConsumerService consumer = message -> {
            System.out.println("Adding to result" + message);
            result.add(message);
        };
        Logger logger = new Logger(consumer);
        for (int i = 0; i < 10; i++) {
            logger.log("String " + i);
        }
        Thread.sleep(100);
        Assertions.assertThat(logger.shutdown(10000, TimeUnit.MILLISECONDS))
            .isTrue();

        Assertions.assertThat(result).hasSize(10);
        for (int i = 0; i < result.size(); i++) {
            Assertions.assertThat(result.get(i))
                .isEqualTo("String " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 100, 1000, 2000})
    void shouldLogInConcurrentThread(int threads) throws InterruptedException, BrokenBarrierException {
        List<String> result = new ArrayList<>();
        ConsumerService consumer = result::add;
        Logger logger = new Logger(consumer);
        CyclicBarrier barrier = new CyclicBarrier(threads + 1);
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            int idx = i;
            executor.submit(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        logger.log("String " + idx + j);
                    }
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    System.out.println("Failed");
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
        }
        barrier.await(); // wait till all the messages are added
        Assertions.assertThat(logger.shutdown(10000, TimeUnit.MILLISECONDS))
            .isTrue();

        Assertions.assertThat(result).hasSize(threads * 10);
        for (int i = 0; i < threads; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertThat(result)
                    .contains("String " + i + j);
            }
        }
    }
}