package com.leetcode.dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> duplicates = new HashMap<>();

        for(int num: nums) {
            int count = duplicates.getOrDefault(num, 0);
            duplicates.put(num, count+1);
        }
        int[] unique = new int[duplicates.size()];

        int index = 0;
        for (Integer num : duplicates.keySet()) {
            unique[index] = num;
            index++;
        }

        Arrays.sort(unique);
        int[] dp = new int[index];

        dp[0] = unique[0] * duplicates.get(unique[0]);
        if(unique[1] - unique[0] > 1) {
            dp[1] = unique[1] * duplicates.get(unique[1]) + dp[0];
        } else {
            dp[1] = Math.max(unique[1] * duplicates.get(unique[1]), dp[0]);
        }

        for(int i = 2; i < unique.length; i++) {
            if (unique[i] - unique[i-1] > 1) {
                dp[i] = unique[i] * duplicates.get(unique[i]) + dp[i-1];
            } else {
                dp[i] = Math.max(
                    unique[i] * duplicates.get(unique[i]) + dp[i-2],
                    dp[i - 1]
                );
            }
        }

        return Math.max(dp[dp.length - 1], dp[dp.length - 2]);
    }

    public static void main(String[] args) {
        DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
//        System.out.println(deleteAndEarn.deleteAndEarn(new int[]{3,4,2}));
        System.out.println(deleteAndEarn.deleteAndEarn(new int[]{1,1,1,2,4,5,5,5,6}));
    }
}
