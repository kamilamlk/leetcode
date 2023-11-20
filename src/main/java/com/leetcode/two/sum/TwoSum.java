package com.leetcode.two.sum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * You can return the answer in any order.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     * Example 2:
     * <p>
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     * Example 3:
     * <p>
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> complements = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (complements.containsKey(nums[i])) {
                return new int[] {complements.get(nums[i]), i};
            }
            complements.put(target - nums[i], i);
        }
        return new int[0];
    }

    /**
     * {5, 2, 4}
     * 6
     * 5 + 1 = 6
     * 1 is a complement value
     * HashMap
     * K                  -> V
     * 1 (complement value) -> 0 (index)
     */
}
