package com.leetcode.dynamic;

import java.util.List;

public class Triangle {
    /**
     *    2          2
     *   3 4        5 6
     *  6 5 7     11 10 13
     * 4 1 8 3   15 11 18 16
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < triangle.size(); i++) {

            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    dp[i][j] = triangle.get(i).get(j) + dp[i-1][j];
                } else if (j == triangle.get(i).size() - 1) {
                    dp[i][j] = triangle.get(i).get(j) + dp[i-1][j-1];
                } else {
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i-1][j -1], dp[i-1][j]);
                }
                if (i == triangle.size() - 1) {
                    min = Math.min(min, dp[i][j]);
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        List<List<Integer>> triangleList = List.of(
            List.of(2),
            List.of(3, 4),
            List.of(6, 5, 7),
            List.of(4, 1, 8, 3)
        );
        System.out.println(triangle.minimumTotal(triangleList));

        List<List<Integer>> shortTriangle = List.of(
            List.of(-10)
        );
        System.out.println(triangle.minimumTotal(shortTriangle));
    }
}
