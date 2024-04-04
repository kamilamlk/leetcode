package com.leetcode.pointers;

public class SortColor {
    // [2,0,2,1,1,0]
    public void sortColors(int[] nums) {
        sortByPointers(nums);
    }

    void sortByPointers(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int k = 0;
        while (i <= j) {
            if (nums[i] == 0) {
                swap(nums, i, k);
                k++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, j);
                j--;
            }
            else {
                i++;
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    void sortByIncrement(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = 0;
        for (int num : nums) {
            if (num == 0) {
                red++;
            } else if (num == 1) {
                white++;
            } else if (num == 2) {
                blue++;
            }
        }
        for (int i = 0; i < red; i++) {
            nums[i] = 0;
        }

        for (int i = red; i < white + red; i++) {
            nums[i] = 1;
        }

        for (int i = red + white; i < white + red + blue; i++) {
            nums[i] = 2;
        }
    }
}
