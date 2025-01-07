package com.leetcode.dynamic;

public class MaxSquare {
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int[] dp = new int[matrix[0].length + 1];
        int diagonal = 0;

        for (char[] chars : matrix) {
            for (int j = 1; j <= chars.length; j++) {
                if (chars[j - 1] == '1') {
                    int tmp = dp[j];
                    dp[j] = 1 + Math.min(dp[j], Math.min(diagonal, dp[j - 1]));
                    max = Math.max(max, dp[j]);
                    diagonal = tmp;
                } else {
                    dp[j] = 0;
                }
            }
        }
        return max * max;
    }

    public static void main(String[] args) {
        MaxSquare maxSquare = new MaxSquare();
        char[][] matrix = new char[][]{
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println(maxSquare.maximalSquare(matrix));
        matrix = new char[][]{{'0', '1'}, {'1', '0'}};
        System.out.println(maxSquare.maximalSquare(matrix));
        matrix = new char[][]{{'0'}};
        System.out.println(maxSquare.maximalSquare(matrix));
        matrix = new char[][]{{'0'},{'1'}};
        System.out.println(maxSquare.maximalSquare(matrix));
        matrix = new char[][]{
            {'1','0','1','0'},
            {'1','0','1','1'},
            {'1','0','1','1'},
            {'1','1','1','1'}
        };
        System.out.println(maxSquare.maximalSquare(matrix));
    }
}
