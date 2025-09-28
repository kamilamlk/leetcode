package com.leetcode.design.coordination;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Startup {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Service("DB", latch));
        executor.submit(new Service("Cache", latch));
        executor.submit(new Service("MessageBroker", latch));

        try {
            if (latch.await(2, TimeUnit.SECONDS)) {
                System.out.println("✅ Application is started");
            } else {
                System.out.println("⏰ Startup timeout — not all services are ready!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdown();
        }
    }

    private static class Service implements Runnable {
        private final String name;
        private final CountDownLatch latch;

        public Service(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((long) (Math.random() * 5000));
                System.out.println(name + " is started");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        }
    }
}