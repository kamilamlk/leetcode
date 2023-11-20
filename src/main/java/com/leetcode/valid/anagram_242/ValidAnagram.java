package com.leetcode.valid.anagram_242;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    /**
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     * Example 1:
     *
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * Example 2:
     *
     * Input: s = "rat", t = "car"
     * Output: false
     *
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 5 * 104
     * s and t consist of lowercase English letters.
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> letter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            put(letter, s.charAt(i), t.charAt(i));
        }
        return letter.values().stream().noneMatch(i -> i != 0);
    }

    private void put(Map<Character, Integer> map, char s_char, char t_char) {
        int s_ap = map.getOrDefault(s_char, 0);
        s_ap++;
        map.put(s_char, s_ap);
        int t_ap = map.getOrDefault(t_char, 0);
        t_ap--;
        map.put(t_char, t_ap);
    }
}
