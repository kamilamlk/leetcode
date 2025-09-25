package com.leetcode.design.job;

import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class JobScheduler {
    private final ScheduledExecutorService executor;
    private final DelayQueue<Job> delayQueue;
    private AtomicBoolean running;
    private final Thread dispatcher;

    public JobScheduler(int poolSize) {
        running = new AtomicBoolean(true);
        executor = Executors.newScheduledThreadPool(poolSize);
        delayQueue = new DelayQueue<>();
        dispatcher = new Thread(this::run);
        dispatcher.start();
    }

    private void run() {
        while (running.get()) {
            try {
                Job job = delayQueue.take();
                executor.submit(() -> {
                    try {
                        job.runnable.run();
                        if (running.get() && job.reschedule(true)) {
                            System.out.println("Rescheduling job " + job.nextExecutionTime );
                            delayQueue.add(job);
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        if (running.get() && job.reschedule(false)) {
                            System.out.println("Rescheduling job " + job.nextExecutionTime );
                            delayQueue.add(job);
                        }
                    }
                });
            } catch (InterruptedException e) {
                // gracefull shutdown
            }
        }
        executor.shutdown();
    }

    public boolean addJob(Runnable runnable) {
        if (!running.get()) return false;
        Job job = new Job(
            UUID.randomUUID().toString(),
            runnable,
            3_000L
        );
        delayQueue.add(job);
        return true;
    }

    public void shutdown() {
        dispatcher.interrupt();
        running.getAndSet(false);
    }

    public static class Job implements Delayed {
        String jobId;
        Runnable runnable;
        int attempts;
        long nextExecutionTime;
        long intervalMills;

        public Job(final String jobId,
                   final Runnable runnable,
                   final long intervalMills) {
            this.jobId = jobId;
            this.runnable = runnable;
            this.attempts = 0;
            this.intervalMills = intervalMills;
            this.nextExecutionTime = System.currentTimeMillis() + intervalMills;
        }

        @Override
        public long getDelay(final TimeUnit unit) {
            return unit.convert(nextExecutionTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(final Delayed other) {
            return Long.compare(this.nextExecutionTime, ((Job) other).nextExecutionTime);
        }

        public boolean reschedule(boolean succeeds) {
            if (succeeds) {
                attempts = 0;
                nextExecutionTime = System.currentTimeMillis() + intervalMills;
            } else {
                if (attempts > 5) return false;
                attempts++;
                nextExecutionTime = System.currentTimeMillis()  + 1000L * (1L << attempts);
            }
            return true;
        }
    }
}
