package com.leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RepeatedDNASequences {
    /**
     * String contains 10 chars - each 2 bits - so we can use 20 bits to represent a sequence
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        if (len <= 10 || len >= 10000) return new ArrayList<>();

        // Map DNA chars to 2-bit codes
        Map<Character, Integer> map = Map.of(
            'A', 0, 'C', 1,
            'G', 2, 'T', 3
        );
        Set<Integer> seen = new HashSet<>();       // stores substrings we’ve already seen
        Set<String> repeated = new HashSet<>();   // stores substrings that occur more than once

        int code = 0;
        // build first window of size 10
        for (int i = 0; i < 10; i++) {
            code = (code << 2) | map.get(s.charAt(i)); // move left by 2 bits and add new char
        }
        seen.add(code);

        // mask to keep only 20 bits
        int mask = (1 << 20) - 1;
        for (int i = 10; i < len; i++) {
            code = ((code << 2) | map.get(s.charAt(i))) & mask;
            if (!seen.add(code)) {
                repeated.add(s.substring(i - 9, i + 1));
            }
        }
        return new ArrayList<>(repeated);             // convert set to list
    }
}
