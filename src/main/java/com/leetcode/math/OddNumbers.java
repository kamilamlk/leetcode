package com.leetcode.math;

public class OddNumbers {
    public int countOdds(int low, int high) {
        // 1 2 3 4 5 6 7 8 9 10
        // 2 - 7 = 3 5
        int min = low % 2 == 0 ? low : low - 1;
        int max = high % 2 == 0 ? high : high + 1;
        return (max - min) / 2;
    }
}
