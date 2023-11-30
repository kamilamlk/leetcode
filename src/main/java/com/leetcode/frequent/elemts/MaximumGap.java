package com.leetcode.frequent.elemts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *<p>
 *
 *
 * Example 1:
 *<p>
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 *<p>
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 */
public class MaximumGap {

    public int maximumGap(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = 0;
        for (int i = 1; i < nums.length; i++) {
            gap = max(gap, nums[i] - nums[i - 1]);
        }
        return gap;
    }

    public void sort(int[] array, int n) {
        int max = getMax(array);
        int step = max / (n - 1);
        step = step == 0 ? max : step;
        List<Integer>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (array[i] / step);
            buckets[bucketIndex].add(array[i]);
        }
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                array[index] = buckets[i].get(j);
                index++;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    int getMax(int[] a) {
        int n = a.length;
        int max = a[0];
        for (int i = 1; i < n; i++){
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }


    private int max(int a, int b) {
        return a > b ? a : b;
    }
}
