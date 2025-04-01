package com.leetcode.sliding.window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConcatenatedSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        if(s.length() < words.length) {
            return List.of();
        }
        List<Integer> result = new ArrayList<>();

        int wordLength = words[0].length();
        int n = s.length();
        for(int i = 0; i < n; i++) {
            if (i + words.length * wordLength > n) {
                break;
            }
            if(isConcatenated(s, words, i, wordLength, n)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isConcatenated(String s, String[] words, int i, int wordLength, int n) {
        Set<String> visitedWords = new HashSet<>();
        boolean[] visited = new boolean[words.length];
        for(int j = i; j < Math.min(i + (words.length * wordLength), n); j += wordLength) {
            String substring = s.substring(j, Math.min(j + wordLength, n));
            for (int k = 0; k < words.length; k++) {
                if (words[k].equals(substring)) {
                    if (!visitedWords.contains(substring) || visited[k]) {
                        visitedWords.add(substring);
                    }
                    visited[k] = true;
                    break;
                }
            }
            if (!visitedWords.contains(substring)) {
                return false;
            }
        }
        for (int k = 0; k < words.length; k++) {
            if (!visited[k]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ConcatenatedSubstring cs = new ConcatenatedSubstring();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","word"};
//        System.out.println(cs.findSubstring(s, words));

//        System.out.println(cs.findSubstring("barfoothefoobarman", new String[]{"foo","bar"})); // Output: [0, 9]
        System.out.println(cs.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"})); // Output: [8]
//        System.out.println(cs.findSubstring("a", new String[]{"a"})); // Output: [0]

//        System.out.println(cs.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"})); // Output: [6,9,12]
    }
}
