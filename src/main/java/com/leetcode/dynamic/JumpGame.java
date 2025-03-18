package com.leetcode.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JumpGame {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(nums, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < nums.length; i++) {
            int max = Math.min(nums[i] + i + 1, nums.length);
            for(int j = i + 1; j < max; j++) {
                dp[j] = Math.min(dp[i] + 1, dp[j]);
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        // sort array desc
        var list = new ArrayList<>();
        list.add(1);

        System.out.println(jumpGame.jump(new int[]{2, 3, 1, 1, 4}));
        //System.out.println(jumpGame.jump(new int[]{2, 3, 0, 1, 4}));
    }
}
