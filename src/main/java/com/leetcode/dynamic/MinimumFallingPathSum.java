package com.leetcode.dynamic;

public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], dp[i-1][j+1]);
                } else if (j == matrix[i].length-1) {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], dp[i-1][j-1]);
                } else {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i-1][j+1]));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[matrix.length-1].length; i++) {
            if (dp[matrix.length-1][i] < min) {
                min = dp[matrix.length-1][i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        MinimumFallingPathSum minimumFallingPathSum = new MinimumFallingPathSum();
        int[][] A = new int[][]{
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };
        System.out.println(minimumFallingPathSum.minFallingPathSum(A));
    }
}
