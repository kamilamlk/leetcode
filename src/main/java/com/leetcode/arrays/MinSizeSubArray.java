package com.leetcode.arrays;

public class MinSizeSubArray {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int left = 0;
        int right = 0;
        while (sum <= target) {
            sum += nums[right++];
        }
        for (int i = 0; i < nums.length; i++) {
            
        }
    }

    public static void main(String[] args) {
        MinSizeSubArray minSizeSubArray = new MinSizeSubArray();
//        int[] nums = {2, 3, 1, 2, 4, 3};
//        System.out.println(minSizeSubArray.minSubArrayLen(7, nums));
//        int[] nums2 = {1, 4, 4};
//        System.out.println(minSizeSubArray.minSubArrayLen(4, nums2));
//        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
//        System.out.println(minSizeSubArray.minSubArrayLen(11, nums3));
//        int[] nums4 = {1, 2, 3, 4, 5};
//        System.out.println(minSizeSubArray.minSubArrayLen(15, nums4));

        int[] nums5 = {12,28,83,4,25,26,25,2,25,25,25,12};
        System.out.println(minSizeSubArray.minSubArrayLen(213, nums5));
    }
}
