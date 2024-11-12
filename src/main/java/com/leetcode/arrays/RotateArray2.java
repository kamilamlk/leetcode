package com.leetcode.arrays;

public class RotateArray2 {
    int steps = 0;
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        reverse(nums, 0, length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, length - 1);

    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
            steps++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        RotateArray2 rotateArray = new RotateArray2();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotateArray.rotate(nums, 3);
        System.out.println("Steps: " + rotateArray.steps);
        rotateArray.steps = 0;
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] nums2 = {-1, -100, 3, 99};
        rotateArray.rotate(nums2, 2);
        System.out.println("Steps: " + rotateArray.steps);
        rotateArray.steps = 0;
        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }
}
