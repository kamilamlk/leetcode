package com.leetcode.dynamic;

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = dp[0] + nums[2];
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i -2], dp[i - 3]) + nums[i];
        }

        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }

    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        System.out.println(robber.rob(new int[]{1,2,3,1}));
        System.out.println(robber.rob(new int[]{2,7,9,3,1}));
    }
}
