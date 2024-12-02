package com.leetcode.queue.stack;

public class TargetSum {
    // Try with dynamic array
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }

    private int dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }

        int value = nums[index];
        int add = dfs(nums, target, index + 1, sum + value);
        int sub = dfs(nums, target, index + 1, sum - value);

        return add + sub;
    }

    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        System.out.println(targetSum.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
