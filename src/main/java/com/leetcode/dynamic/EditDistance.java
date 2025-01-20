package com.leetcode.dynamic;

public class EditDistance {
    private enum Operation {
        INSERT, DELETE, REPLACE
    }
    /**
     * X, Y
     * dp[i, j] - edit distance between X[1..i] and Y[1..j]
     * @return minimum number of operations required to convert word1 to word2
     */
    public int minDistance(String word1, String word2) {

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                dp[i][j] = Math.min(
                    dp[i-1][j] + 1,
                    Math.min(
                        dp[i][j-1] + 1,
                        dp[i-1][j-1] + (word1.charAt(i) == word2.charAt(j) ? 0 : 1)
                    )
                );
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("horse", "ros"));
        System.out.println(editDistance.minDistance("intention", "execution"));
    }
}
