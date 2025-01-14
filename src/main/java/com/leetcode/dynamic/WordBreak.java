package com.leetcode.dynamic;

import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        int max = max(wordDict);
        dp[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = i-1; j >= Math.max(i - max, 0); j--) {
                String sub = s.substring(j, i);
                if (dp[j] && wordDict.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    private int max(List<String> wordDict) {
        int max = 0;
        for (String string : wordDict) {
            if (string.length() > max) {
                max = string.length();
            }
        }
        return max;
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak("leetcode", List.of("leet", "code")));
        System.out.println(wordBreak.wordBreak("leedcode", List.of("leet", "code")));
        System.out.println(wordBreak.wordBreak("applepenapple",  List.of("apple","pen")));
        System.out.println(wordBreak.wordBreak("catsandog",  List.of("cats","dog","sand","and","cat")));
    }
} 
