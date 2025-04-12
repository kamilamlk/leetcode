package com.leetcode.sliding.window;

public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        int[] diff = new int[nums.length];
        for(int i = 0; i < nums.length - 1; i++) {
            diff[i] = Math.abs(nums[i + 1] - nums[i]);
        }
        int count = 0;
        int left = 0;
        while(left < nums.length - 2) {
            int right = left + 1;
            while(right < nums.length - 1 && diff[left] == diff[right]) {
                count += right - left;
                right++;
            }
            left = right;
        }
        return count;
    }

    public static void main(String[] args) {
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{}));
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5}));
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1, 2, 3}));
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1, 2}));
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1}));



    }
}
