package com.leetcode.pointers;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            if (i == 0) {
                sum = nums[i] + nums[j] + nums[k];
            }
            while (j < k) {
                int temp = nums[i] + nums[j] + nums[k];
                if (temp > target) {
                    k--;
                } else if (temp < target) {
                    j++;
                } else {
                    return temp;
                }
                sum = closestSum(target, sum, temp);
            }
        }
        return sum;
    }
    //-4,-1, 1,2
    private int closestSum(int target, int prevSum, int newSum) {
        if (Math.abs(target - prevSum) < Math.abs(target - newSum)) {
            return prevSum;
        } else {
            return newSum;
        }
    }
}
