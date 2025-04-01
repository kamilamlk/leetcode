package com.leetcode.sliding.window;

public class MinSubarray {
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArray = Integer.MAX_VALUE;
        int sum = nums[0];
        int j = 1;
        for(int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            while (j < nums.length && sum < target) {
                sum += nums[j++];
            }

            if(sum >= target) {
                minSubArray = Math.min(minSubArray, j - i);
            }
        }
        return minSubArray == Integer.MAX_VALUE ? 0 : minSubArray;
    }

    public static void main(String[] args) {

    }
}
