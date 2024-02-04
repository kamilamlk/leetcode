package com.leetcode.pointers;

import java.util.List;

public class PairsSumLessThanTarget {
    public int countPairs(List<Integer> nums, int target) {
        nums.sort(Integer::compareTo);
        int k = 0;
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                sum = nums.get(i) + nums.get(j);
                if (sum < target) {
                    k++;
                }
            }
        }
        return k;
    }
}
