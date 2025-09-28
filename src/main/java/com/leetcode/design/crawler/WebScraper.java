package com.leetcode.design.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebScraper {
    public int getTotalSize(List<String> pages) {
        ExecutorService executor = Executors.newFixedThreadPool(
            Math.min(
                (int) (Runtime.getRuntime().availableProcessors()),
                pages.size() / 2
            )
        );
        List<Future<Integer>> futures = new ArrayList<>();
        for (var page : pages) {
            Future<Integer> future = executor.submit(() -> {
                try {
                    Thread.sleep(5);
                    return 10;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            futures.add(future);
        }
        int sum = 0;
        executor.shutdown();
        try {
            for (var future : futures) {
                sum += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return sum;
    }
}
