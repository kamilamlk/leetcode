package com.leetcode.math;

public class MonotonicArray {
    public boolean isMonotonic(int[] nums) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                increasing = false;
            }
            if (nums[i] > nums[i - 1]) {
                decreasing = false;
            }
        }
        return increasing || decreasing;
    }

    public static void main(String[] args) {
        MonotonicArray ma = new MonotonicArray();
        System.out.println(ma.isMonotonic(new int[]{1, 2, 2, 3}));
        System.out.println(ma.isMonotonic(new int[]{6, 5, 4, 4}));
        System.out.println(ma.isMonotonic(new int[]{1, 3, 2}));
        System.out.println(ma.isMonotonic(new int[]{1, 2, 4, 5}));
        System.out.println(ma.isMonotonic(new int[]{1, 1, 1}));
    }
}
