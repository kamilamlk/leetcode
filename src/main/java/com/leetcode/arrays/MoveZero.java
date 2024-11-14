package com.leetcode.arrays;

public class MoveZero {
    public void moveZeroes(int[] nums) {
        int zeroIndex = 0;
        while (zeroIndex < nums.length && nums[zeroIndex] != 0) {
            zeroIndex++;
        }
        int i = zeroIndex + 1;
        while (i < nums.length) {
            if (nums[i] != 0) {
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                zeroIndex++;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        MoveZero moveZero = new MoveZero();
        int[] nums = {0, 1, 0, 3, 12};
        moveZero.moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] nums2 = {0};
        moveZero.moveZeroes(nums2);
        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }
}
