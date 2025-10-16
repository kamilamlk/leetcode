package com.leetcode.design.retry;

import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Retrier<T> {
    private final int maxAttempts;
    private final Thread dispatcher;

    private final DelayQueue<Task<T>> queue;
    private final AtomicBoolean running;

    public Retrier(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.queue = new DelayQueue<>();
        this.running = new AtomicBoolean(true);
        this.dispatcher = new Thread(this::run);
        dispatcher.start();
    }

    private void  run() {
        while (running.get()) {
            try {
                Task<T> task = queue.take();
                if (!task.run()) {
                    task.reschedule();
                    queue.offer(task);
                }
            } catch (InterruptedException e) {
                // gracefull shutdown
            }
        }
    }

    public Future<T> retry(Callable<T> supplier) {
        FutureTask<T> future = new FutureTask<>(supplier);
        Task<T> task = new Task<>(maxAttempts, future);
        queue.add(task);

        return future;
    }


    public void shutdown() {
        running.getAndSet(false);
    }
    private static class Task<T> implements Delayed {
        int maxAttempts;
        int attempts;
        long nextExecutionTime;
        FutureTask<T> future;

        public Task(final int maxAttempts,
                    final FutureTask<T> future) {
            this.maxAttempts = maxAttempts;
            this.future = future;
        }

        @Override
        public long getDelay(final TimeUnit unit) {
            return unit.convert(nextExecutionTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(final Delayed o) {
            return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        public boolean run() {
            try {
                future.run();
                return true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                if (attempts >= maxAttempts) {
                    future.cancel(true);
                    return true;
                }
                return false;
            }
        }

        public void reschedule() {
            attempts++;
            nextExecutionTime = System.currentTimeMillis()  + 1000L * (1L << attempts);
        }
    }
}
