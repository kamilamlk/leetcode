package com.leetcode.pointers;

public class RemoveElement {
    int timeComplexity = 0;
    // nums = [3,2,2,3], val = 3
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        int k = 0;
        while (i <= j) {
            if (nums[i] == val) {
                swap(nums, i, j);
                j--;
            } else {
                k++;
                i++;
            }
            timeComplexity++;
        }
        return k;
    }

    private void swap(int[] nums, int i, int j) {
        int a = nums[i];
        nums[i] = nums[j];
        nums[j] = a;
    }
}
