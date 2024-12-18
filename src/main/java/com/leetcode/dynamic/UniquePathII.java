package com.leetcode.dynamic;

public class UniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        dp[0][0] = 1;
        for(int i = 0; i < obstacleGrid.length; i++) {
            for(int j = 0; j < obstacleGrid[i].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if(obstacleGrid[i][j] == 0) {
                    if (i == 0) {
                        dp[i][j] = dp[i][j-1];
                    } else if (j == 0) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j] + dp[i][j-1];
                    }
                }
            }
        }
        return dp[obstacleGrid.length-1][obstacleGrid[obstacleGrid.length -1].length - 1];
    }

    public static void main(String[] args) {
        UniquePathII uniquePathII = new UniquePathII();
        int[][] obstacleGrid = new int[][]{{0, 1, 0, 0}};
        System.out.println(uniquePathII.uniquePathsWithObstacles(obstacleGrid));
    }
}
