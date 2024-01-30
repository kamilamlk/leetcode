package com.leetcode.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // [-1,0,1,2,-1,-4]
        Arrays.sort(nums);
        // [-4,-1,-1,0,1,2]
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // i = 0; k = 1; j = 5
            int k = i + 1;
            int j =  nums.length - 1;
            while (k < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    k++;
                } else if (sum > 0) {
                    j--;
                } else {
                    triplets.add(List.of(nums[i], nums[k], nums[j]));
                    while (nums[i] == nums[i+1] && i < k) {
                        i++;
                    }
                }
            }
        }
        return triplets;
    }

}
