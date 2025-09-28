package com.leetcode.design.logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Logger {
    private static final String POISON_PILL = "__STOP__";

    private final BlockingQueue<String> queue;
    private final ExecutorService executor;
    private final ConsumerService consumer;
    private final AtomicBoolean running;

    public Logger(ConsumerService consumer) {
        this.queue = new LinkedBlockingQueue<>();
        this.consumer = consumer;

        this.running = new AtomicBoolean(true);
        this.executor = Executors.newSingleThreadExecutor();
        executor.submit(this::consume);
    }

    private void consume() {
        while (true) {
            try {
                String message = queue.take();
                if (POISON_PILL.equals(message)) {
                    break;
                }
                consumer.consume(message);
            } catch (InterruptedException e) {
                System.out.println("Interrupted " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void log(String message) {
        try {
            if (running.get()) {
                queue.put(message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    // graceful shutdown
    public boolean shutdown(long duration, TimeUnit unit) throws InterruptedException {
        running.getAndSet(false); // forbid adding new
        queue.add(POISON_PILL);
        executor.shutdown(); // init shutdown
        return executor.awaitTermination(duration, unit);
    }
}
