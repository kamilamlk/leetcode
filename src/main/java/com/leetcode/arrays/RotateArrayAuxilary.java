package com.leetcode.arrays;

public class RotateArrayAuxilary {
    // Time complexity O(n)
    // Space complexity O(k)
    // left rotate
    public void rotate(int[] nums, int k) {
        int[] auxilary = new int[k];
        for (int i = 0; i < auxilary.length; i++) {
            auxilary[i] = nums[i];
        }
        for (int i = 0; i < nums.length - k; i++) {
            nums[i] = nums[i + k];
        }
        for (int i = 0; i < k; i++) {
            nums[i + nums.length - k] = auxilary[i];
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        RotateArrayAuxilary rotateArray = new RotateArrayAuxilary();
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
