package com.leetcode.sliding.window;

public class ShortestSubarray {
    public int minimumSubarrayLength(int[] nums, int k) {
        int special = 1;
        int minLength = nums.length + 1;
        int j = 1;
        special |= nums[0];
        for(int i = 0; i < nums.length; i++) {
            while(j < nums.length && special < k) {
                special |= nums[j++];
            }
            if(special >= k) {
                minLength = Math.min(minLength, j - i);
            }
            special &= ~nums[i];
        }

        return minLength == nums.length + 1 ? -1 : minLength;
    }

    public static void main(String[] args) {
        ShortestSubarray shortestSubarray = new ShortestSubarray();
        System.out.println(shortestSubarray.minimumSubarrayLength(new int[]{1, 2, 3}, 2));
        System.out.println(shortestSubarray.minimumSubarrayLength(new int[]{1, 2}, 0));

    }
}
