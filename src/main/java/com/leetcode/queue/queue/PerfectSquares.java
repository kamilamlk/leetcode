package com.leetcode.queue.queue;

import java.util.Arrays;

public class PerfectSquares {
    // Rethink the problem with dynamic programming
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n); // 1^2 x n
        dp[0] = 0;          // no way
        dp[1] = 1;          // 1^2

        for (int i = 2; i <= n; ++i)
            for (int j = 1; j * j <= i; ++j)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);

        return dp[n];
    }


    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        System.out.println(perfectSquares.numSquares(12));
        System.out.println(perfectSquares.numSquares(13));
        System.out.println(perfectSquares.numSquares(7168));
    }
}
