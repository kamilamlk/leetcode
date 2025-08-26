package com.leetcode.string;

public class DecodeWays {
    public int numDecodings(String s) {
        if(s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + oneDigit;
            if(oneDigit != 0) {
                dp[i] += dp[i-1];
            }

            if(twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodings("226")); // Expected: 3
        // System.out.println(decodeWays.numDecodings("12")); // Expected: 2
        // System.out.println(decodeWays.numDecodings("06")); // Expected: 0
    }
}
