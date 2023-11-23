package com.leetcode.group.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {
    /**
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
     *<p>
     *
     *
     * Example 1:
     *<p>
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * Example 2:
     *<p>
     * Input: strs = [""]
     * Output: [[""]]
     * Example 3:
     *<p>
     * Input: strs = ["a"]
     * Output: [["a"]]
     *<p>
     * Constraints:
     * 1 <= strs.length <= 104
     * 0 <= strs[i].length <= 100
     * strs[i] consists of lowercase English letters.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] word = str.toCharArray();
            Arrays.sort(word);
            String key = new String(word);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
