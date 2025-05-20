package com.leetcode.ocp.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkAnJoinExample {
    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10;
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int mid = (start + end) / 2;
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);
                leftTask.fork(); // Fork the left task
                rightTask.fork(); // Fork the right task
                long rightResult = rightTask.join(); // Compute the right task
                long leftResult = leftTask.join(); // Join the left task
                return leftResult + rightResult; // Combine results
            }
        }
    }
    public static void main(String[] args) {
        int[] array = new int[1000000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        long forkResult = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("ForkJoin result: " + forkResult + " in " + (endTime - startTime) + " ms");

        long startTime2 = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("Sequential result: " + sum + " in " + (endTime2 - startTime2) + " ms");
    }
}
