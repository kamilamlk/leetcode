package com.leetcode.dynamic;

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        return 0;
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (s.charAt(left) == s.charAt(right)) {

            }
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence longestPalindromicSubsequence = new LongestPalindromicSubsequence();
        System.out.println(longestPalindromicSubsequence.longestPalindromeSubseq("babab"));
        System.out.println(longestPalindromicSubsequence.longestPalindromeSubseq("cbbd"));
        System.out.println(longestPalindromicSubsequence.longestPalindromeSubseq("a"));
        System.out.println(longestPalindromicSubsequence.longestPalindromeSubseq("ac"));
    }
}
