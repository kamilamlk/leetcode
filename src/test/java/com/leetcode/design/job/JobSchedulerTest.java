package com.leetcode.design.job;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

class JobSchedulerTest {
    @Test
    void shouldRunOneJob() throws InterruptedException {
        JobScheduler scheduler = new JobScheduler(5);
        scheduler.addJob(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("I'm running");
            }
        });
        scheduler.shutdown();
    }

    @Test
    void shouldRunConcurrentJob() throws InterruptedException {
        JobScheduler scheduler = new JobScheduler(5);
        AtomicLong job1Prev = new AtomicLong(System.currentTimeMillis());
        scheduler.addJob(() -> {
            System.out.println("Job 1 is running: " + System.currentTimeMillis());
            long current = System.currentTimeMillis();
            long prev = job1Prev.getAndSet(current);
            System.out.println(current - prev);
        });
        scheduler.addJob(() -> {
            System.out.println("Job 2 is running: " + System.currentTimeMillis());
        });
        Thread.sleep(10_000);
        scheduler.shutdown();
    }
}