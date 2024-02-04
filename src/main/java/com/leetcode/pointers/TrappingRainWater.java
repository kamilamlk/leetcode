package com.leetcode.pointers;

public class TrappingRainWater {
    public int trap(int[] height) {
        int water = 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int left = 0; int right = height.length - 1;
        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                if (leftMax < height[left]) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
            } else {
                right--;
                if (rightMax < height[right]) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
            }
        }
        return water;
    }
}
