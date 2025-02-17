package com.leetcode.string;

public class EditDistance {
    private int word1Length;
    public int minDistance(String word1, String word2) {
        word1Length = word1.length();
        return minDistance(word1, word2, 0, 0);
    }

    private int minDistance(String word1, String word2, int i, int j) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (word2.length() <= j) {
            return Integer.MAX_VALUE;
        }
        if (word1.length() <= i) {
            return Integer.MAX_VALUE;
        }

        int replaced = minDistance(replace(word1, word2, i, j), word2, i+1, j+1);
        int deleted = minDistance(delete(word1, i), word2, i, j+1);
        int inserted = minDistance(insert(word1, word2, i, j), word2, i+1, j+1);
        int nothing = minDistance(word1, word2, i+1, j+1);

        return Math.min(
            replaced,
            Math.min(deleted, Math.min(inserted, nothing))
        ) + 1;
    }

    private String insert(String word1, String word2, int i, int j) {
        return word1.substring(0, i) + word2.charAt(j) + word1.substring(i);
    }

    private String delete(String word1, int i) {
        return word1.substring(0, i) + word1.substring(i + 1);
    }

    private String replace(String word1, String word2, int i, int j) {
        return word1.substring(0, i) + word2.charAt(j) + word1.substring(i + 1);
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("horse", "ros"));
        System.out.println(editDistance.minDistance("intention", "execution"));
    }
}
