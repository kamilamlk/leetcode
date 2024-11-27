package com.leetcode.queue.stack;

import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] dailyTemperatures = new int[temperatures.length];

        Deque<Integer> stackIdx = new java.util.LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stackIdx.isEmpty() && temperatures[i] > temperatures[stackIdx.peek()]) {
                int idx = stackIdx.pop();
                dailyTemperatures[idx] = i - idx;
            }
            stackIdx.push(i);
        }
        return dailyTemperatures;
    }

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures.dailyTemperatures(temperatures);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
