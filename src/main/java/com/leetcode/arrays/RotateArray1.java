package com.leetcode.arrays;

public class RotateArray1 {
    // Time complexity O(n)
    // Space complexity O(1)
    public void rotate(int[] nums, int k) {
        int swaps = 0;
        int current = 0;
        while (swaps < nums.length) {
            int next = next(current, k, nums.length);
            while (next != current) {
                swap(nums, next, current);
                swaps++;
                next = next(next, k, nums.length);
            }
            current++;
            swaps++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int next(int current, int k, int length) {
        return (current + k + length) % length;
    }

    public static void main(String[] args) {
        RotateArray1 rotateArray = new RotateArray1();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotateArray.rotate(nums, 3);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] nums2 = {-1, -100, 3, 99};
        rotateArray.rotate(nums2, 2);
        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }
}
