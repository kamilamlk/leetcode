package com.leetcode.design.coordination;

import org.junit.jupiter.api.Test;


import java.util.concurrent.ExecutionException;

class RepeatedTaskTest {
    @Test
    void shouldCompute() {
        int[] array = new int[1_000_000_000];

        for (int i = 1; i < array.length; i++) {
            array[i] = i;
        }
        long sum = 0;
        long start = System.currentTimeMillis();
        for (int num: array) {
            sum += num;
        }
        long end = System.currentTimeMillis();
        System.out.println("Calculate " + sum + " in " + (end - start));

        start = System.currentTimeMillis();
        sum = new RepeatedTask(array).compute();
        end = System.currentTimeMillis();

        System.out.println("Calculate with CyclicBarrier " + sum + " in " + (end - start));
    }
}