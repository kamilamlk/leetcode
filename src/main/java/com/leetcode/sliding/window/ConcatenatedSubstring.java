package com.leetcode.sliding.window;

import java.util.*;

public class ConcatenatedSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) return result;

        int wordLength = words[0].length();
        int numWords = words.length;

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }


        for (int i = 0; i < wordLength; i++) {
            int left = i, right = i;
            int count = 0;
            Map<String, Integer> windowMap = new HashMap<>();

            while (right + wordLength <= s.length()) {
                String word = s.substring(right, right + wordLength);
                right += wordLength;

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;


                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        left += wordLength;
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        count--;
                    }


                    if (count == numWords) {
                        result.add(left);
                    }
                } else {

                    windowMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        ConcatenatedSubstring solution = new ConcatenatedSubstring();
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"})); // []
    }
}