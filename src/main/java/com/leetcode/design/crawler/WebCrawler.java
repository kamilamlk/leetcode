package com.leetcode.design.crawler;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class WebCrawler {
    private final Map<String, List<String>> crawledUrls;
    private final ExecutorService executor;
    private final Deque<String> queue;

    public WebCrawler(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
        this.crawledUrls = new ConcurrentHashMap<>();
        this.queue = new LinkedBlockingDeque<>();
    }

    public void crawl(List<String> urls) {
        for (var url : urls) {
            executor.submit(
                () -> {
                    String html = fetch(url);
                    List<String> subUrls = parse(html);
                    crawledUrls.put(url, subUrls);
                }
            );

        }
    }

    public List<String> getSubUrls(String url) {
        return crawledUrls.getOrDefault(url, List.of());
    }


    public void shutdown() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    private String fetch(String url) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return UUID.randomUUID().toString();
    }

    private List<String> parse(String html) {
        int n = (int)(Math.random() * 5) + 1;
        List<String> ursl = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ursl.add(UUID.randomUUID().toString());
        }
        return ursl;
    }
}
