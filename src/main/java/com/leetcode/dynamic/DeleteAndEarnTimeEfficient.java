package com.leetcode.dynamic;


public class DeleteAndEarnTimeEfficient {
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max = Math.max(max,nums[i]);
        }
        int[] dp = new int[max+1];
        for(int i=0;i<n;i++){
            dp[nums[i]]+=nums[i];
        }
        for(int i=2;i<max+1;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+dp[i]);
        }
        return dp[max];
    }

    public static void main(String[] args) {
        DeleteAndEarnTimeEfficient deleteAndEarn = new DeleteAndEarnTimeEfficient();
//        System.out.println(deleteAndEarn.deleteAndEarn(new int[]{3,4,2}));
        System.out.println(deleteAndEarn.deleteAndEarn(new int[]{1,1,1,2,4,5,5,5,6}));
    }
}
