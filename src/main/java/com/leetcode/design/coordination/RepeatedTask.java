package com.leetcode.design.coordination;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RepeatedTask {
    private int[] array;

    public RepeatedTask(final int[] array) {
        this.array = array;
    }

    public long compute() {
        SumTask task = new SumTask(0, array.length);

        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    private class SumTask extends RecursiveTask<Long> {
        int start;
        int end;
        int length;

        public SumTask(final int start,
                       final int end) {
            this.start = start;
            this.end = end;
            this.length = end - start;
        }

        @Override
        protected Long compute() {
            if (length <= 500_000) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            }
            int mid = start + length / 2;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid, end);
            left.fork(); // compute in another thread
            long rightVal = right.compute(); // compute in this thread
            long leftVal = left.join(); // wait for left to finish
            return rightVal + leftVal;
        }
    }
}
