package com.leetcode.arrays;

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray finder = new FindMinimumInRotatedSortedArray();
        int[] nums = {3,1,2};
        System.out.println(finder.findMin(nums)); // Output: 1

    }
}
