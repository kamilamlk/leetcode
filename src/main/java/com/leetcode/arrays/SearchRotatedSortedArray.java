package com.leetcode.arrays;

public class SearchRotatedSortedArray {
    public boolean search(int[] nums, int target) {
        int pivot = getPivot(nums);
        if(nums[pivot] <= target && nums[nums.length - 1] >= target) {
            return binarySearch(nums, target, pivot, nums.length - 1);
        } else return binarySearch(nums, target, 0, pivot - 1);
    }

    private int getPivot(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                // Pivot is in the left half (including mid)
                right = mid;
            } else if (nums[mid] > nums[right]) {
                // Pivot is in the right half
                left = mid + 1;
            } else {
                // nums[mid] == nums[right], can't decide -> shrink right
                right--;
            }
        }
        return left;
    }

    private boolean binarySearch(int[] nums, int target, int left, int right) {
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return true;
            if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        SearchRotatedSortedArray s = new SearchRotatedSortedArray();
        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        int target = 2;
        System.out.println(s.search(nums, target));
    }
}
