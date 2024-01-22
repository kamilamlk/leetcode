package com.leetcode.frequent.elemts;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int maxLength = 0;

        for (Integer i : set) {
            if (!set.contains( i - 1 )) {
                int length = 0;
                while (set.contains(i + length)) {
                    length += 1;
                }
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }
}
