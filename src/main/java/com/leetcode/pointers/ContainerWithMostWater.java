package com.leetcode.pointers;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            int a = height[i];
            int b = height[j];
            if (a < b) {
                max = Math.max(max, a * (j - i));
                i++;
            } else {
                max = Math.max(max, b * (j - i));
                j--;
            }
        }
        return max;
    }
}
