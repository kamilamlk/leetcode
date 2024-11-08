package com.leetcode.arrays;

import java.util.Arrays;

public class ContainsDuplicate {
    /**
     * Given an integer array nums,
     * return true if any value appears at least twice in the array, and return false if every element is distinct.
     *
     * Example 1:
     * Input: nums = [1,2,3,1]
     * Output: true
     * Example 2:
     *
     * Input: nums = [1,2,3,4]
     * Output: false
     * Example 3:
     *
     * Input: nums = [1,1,1,3,3,4,3,2,4,2]
     * Output: true
     * 
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     */
    public boolean containsDuplicates(int[] nums) {
        int temp;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]){
                return true;
            }
        }
        return false;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int pi = partition(nums, low, high);
            quickSort(nums, low, pi - 1);
            quickSort(nums, pi + 1, high);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high];    // pivot
        int i = (low - 1);  // index of smaller element
        for (int j = low; j <= high- 1; j++)
        {
            if (nums[j] < pivot)
            {
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, high);
        return (i + 1);
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
