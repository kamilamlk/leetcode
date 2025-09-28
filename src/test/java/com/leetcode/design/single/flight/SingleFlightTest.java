package com.leetcode.design.single.flight;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

class SingleFlightTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 100})
    void testLoad(int threads) throws ExecutionException, InterruptedException {
        String key = "key1";
        Supplier<String> load = () -> {
            try {
                Thread.sleep(100);
                return key;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };
        SingleFlight singleFlight = new SingleFlight();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            futures.add(
                executor.submit(() -> singleFlight.getOrLoad(key, load).get())
            );
        }

        for (var future : futures) {
            Assertions.assertThat(future.get())
                .isEqualTo(key);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 100})
    void testExceptionLoad(int threads) throws ExecutionException, InterruptedException {
        String key = "key1";
        Supplier<String> load = () -> {
            try {
                Thread.sleep(100);
                throw new CompletionException(new RuntimeException("Supplier error"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };
        SingleFlight singleFlight = new SingleFlight();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<String>> futures = new CopyOnWriteArrayList<>();
        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                futures.add(singleFlight.getOrLoad(key, load));
            });
        }

        for (var future : futures) {
            Assertions.assertThatThrownBy(future::get)
                .isInstanceOf(ExecutionException.class);
        }
    }
}