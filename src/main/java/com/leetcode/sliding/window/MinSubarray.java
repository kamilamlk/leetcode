package com.leetcode.sliding.window;

public class MinSubarray {
    public int minimumSubarrayLength(int[] nums, int k) {
        if(k == 0 && nums.length > 0) {
            return 1;
        }
        int special = 0;
        int minLength = nums.length + 1;
        int j = 1;
        special |= nums[0];
        int i = 0;
        for(; i < nums.length; i++) {
            while(j < nums.length && special < k) {
                special |= nums[j++];
            }
            if(special >= k) {
                minLength = Math.min(minLength, j - i);
            }
            special &= ~nums[i];
        }
        if(special >= k) {
            minLength = Math.min(minLength, j - i);
        }
        return minLength == nums.length + 1 ? -1 : minLength;
    }

    public static void main(String[] args) {
        MinSubarray minSubarray = new MinSubarray();
       // System.out.println(minSubarray.minimumSubarrayLength(new int[]{1, 2, 3}, 2));

        System.out.println(minSubarray.minimumSubarrayLength(new int[]{16,1,2,20,32}, 45));
    }
}
