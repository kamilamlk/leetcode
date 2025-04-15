package com.leetcode.sliding.window;

public class MaxAverage {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        double max = -Double.MAX_VALUE;
        int sum = 0;

        for(int i = 0; i < k; i++) {
            sum+=nums[i];
        }

        max = Math.max(max, (double)sum / k);
        for(int right = k; right < nums.length; right++) {
            sum -= nums[left++];
            sum +=nums[right];
            max = Math.max(max, (double) sum / k);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxAverage maxAverage = new MaxAverage();
        System.out.println(maxAverage.findMaxAverage(new int[]{-1}, 1));
    }
}
