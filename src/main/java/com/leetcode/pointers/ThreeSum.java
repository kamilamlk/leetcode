package com.leetcode.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // O(n log(n)) + O(n^2)

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i -1]) {
                continue;
            }
            int j = i+1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    triplets.add(List.of(nums[i], nums[j], nums[k]));
                    k--;
                    j++;
                    while (nums[k] == nums[k+1] && k > j) {
                        k--;
                    }
                    while (j < k && nums[j] == nums[j-1]) {
                        j++;
                    }
                }
            }
        }
        return triplets;
    }
}
