package com.leetcode.dynamic;

import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        char[] dp = new char[s.length() + 1];


    }

    private boolean dfs(String s, List<String> wordDict, int start, int end) {

    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak("leetcode", List.of("leet", "code")));
        System.out.println(wordBreak.wordBreak("leedcode", List.of("leet", "code")));
        System.out.println(wordBreak.wordBreak("applepenapple",  List.of("apple","pen")));
        System.out.println(wordBreak.wordBreak("catsandog",  List.of("cats","dog","sand","and","cat")));
    }
}
