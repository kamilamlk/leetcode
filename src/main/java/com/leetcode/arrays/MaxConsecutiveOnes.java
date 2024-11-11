package com.leetcode.arrays;

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                k++;
            } else {
                if (k > max) {
                    max = k;
                }
                k = 0;
            }
        }
        return max > k ? max: k;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes maxConsecutiveOnes = new MaxConsecutiveOnes();
        int[] nums = {1, 1, 0, 1, 1, 1};
        System.out.println(maxConsecutiveOnes.findMaxConsecutiveOnes(nums));
        assert maxConsecutiveOnes.findMaxConsecutiveOnes(nums) == 3;
        int[] nums2 = {1, 0, 1, 1, 0, 1};
        assert maxConsecutiveOnes.findMaxConsecutiveOnes(nums2) == 2;
        System.out.println(maxConsecutiveOnes.findMaxConsecutiveOnes(nums2));
    }
}
