package com.leetcode.arrays;

public class LargestTwice {
    /**
     * You are given an integer array nums where the largest integer is unique.
     *
     * Determine whether the largest element in the array is at least twice as much as every other number in the array.
     * If it is, return the index of the largest element, or return -1 otherwise.
     */
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return -1;
        }

        int maxIndex = 0;
        int secondMaxIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                secondMaxIndex = maxIndex;
                maxIndex = i;
            } else if (nums[i] > nums[secondMaxIndex]) {
                secondMaxIndex = i;
            }
        }

        if (nums[maxIndex] >= 2 * nums[secondMaxIndex]) {
            return maxIndex;
        }
        return -1;
    }

    public static void main(String[] args) {
        LargestTwice largestTwice = new LargestTwice();
        int[] nums = {3, 6, 1, 0};
        System.out.println(largestTwice.dominantIndex(nums));
        nums = new int[]{1, 2, 3, 4};
        System.out.println(largestTwice.dominantIndex(nums));

        nums = new int[]{1, 0};
        System.out.println(largestTwice.dominantIndex(nums));
    }
}
