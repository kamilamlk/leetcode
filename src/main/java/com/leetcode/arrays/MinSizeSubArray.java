package com.leetcode.arrays;

public class MinSizeSubArray {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int minSize = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                minSize = Math.min(minSize, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }

    public static void main(String[] args) {
        MinSizeSubArray minSizeSubArray = new MinSizeSubArray();
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSizeSubArray.minSubArrayLen(7, nums));
        int[] nums2 = {1, 4, 4};
        System.out.println(minSizeSubArray.minSubArrayLen(4, nums2));
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(minSizeSubArray.minSubArrayLen(11, nums3));
        int[] nums4 = {1, 2, 3, 4, 5};
        System.out.println(minSizeSubArray.minSubArrayLen(15, nums4));

        int[] nums5 = {12,28,83,4,25,26,25,2,25,25,25,12};
        System.out.println(minSizeSubArray.minSubArrayLen(213, nums5));
    }
}
