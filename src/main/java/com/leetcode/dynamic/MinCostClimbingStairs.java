package com.leetcode.dynamic;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] b = new int[cost.length];
        b[0] = cost[0];
        b[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            b[i] = Math.min(b[i - 1] + cost[i], b[i - 2] + cost[i]);
        }
        return Math.min(b[cost.length - 1], b[cost.length - 2]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs stairs = new MinCostClimbingStairs();
        System.out.println(stairs.minCostClimbingStairs(new int[] {10,15,20}));
        System.out.println(stairs.minCostClimbingStairs(new int[] {1,100,1,1,1,100,1,1,100,1}));
    }
}
