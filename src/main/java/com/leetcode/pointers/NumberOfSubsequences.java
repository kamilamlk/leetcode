package com.leetcode.pointers;

import java.util.Arrays;

public class NumberOfSubsequences {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int counter = 0;

        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            for (int j = nums.length - 1; j > i; j--) {
                if (min + nums[j] <= target) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
