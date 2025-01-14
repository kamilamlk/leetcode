package com.leetcode.dynamic;

public class UniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[] dpPrev = new int[obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dpPrev[i] = 1;
        }
        int dpLeft = dpPrev[obstacleGrid[0].length-1];
        for (int i = 1; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] == 1) {
                dpLeft = 0;
            } else {
                dpLeft = 1;
            }
            for (int j = 1; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dpLeft = 0;
                } else {
                    dpPrev[j-1] = dpLeft;
                    dpLeft = dpLeft + dpPrev[j];
                }
            }
        }
        return dpLeft;
    }

    public static void main(String[] args) {
        UniquePathII uniquePathII = new UniquePathII();
        int[][] obstacleGrid = new int[][]{{0, 1, 0, 0}};

       // System.out.println(uniquePathII.uniquePathsWithObstacles(obstacleGrid));
        System.out.println(uniquePathII.uniquePathsWithObstacles(new int[][]{{1, 0}}));
    }
}
