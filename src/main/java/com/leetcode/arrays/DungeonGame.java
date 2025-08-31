package com.leetcode.arrays;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 0 : -dungeon[m - 1][n - 1];

        for(int i = dungeon[m - 1].length - 2; i >= 0; i--) {
            if(dungeon[m - 1][i] >= dp[m - 1][i + 1]) {
                dp[m - 1][i] = 0;
            } else {
                // not enougth magic on board adding
                dp[m - 1][i] = dp[m - 1][i + 1] - dungeon[m - 1][i];
            }
        }

        for(int i = m - 2; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                // get min next step health
                int next = (j == n - 1) ? dp[i + 1][j] : Math.min(dp[i + 1][j], dp[i][j + 1]);

                if(dungeon[i][j] >= next) {
                    // enough on the board for next step. nothing to add
                    dp[i][j] = 0;
                } else {
                    // not enough magic on board adding
                    dp[i][j] = next - dungeon[i][j];
                }
            }
        }
        return dp[0][0] + 1;
    }

    public static void main(String[] args) {
        DungeonGame game = new DungeonGame();
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(game.calculateMinimumHP(dungeon)); // Output: 7
    }
}
