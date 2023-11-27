package com.leetcode.frequent.elemts;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    /**
     * Given an integer array nums and an integer k, return the k most frequent elements.
     * You may return the answer in any order.
     *<p>
     * Example 1:
     *<p>
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     *<p>
     * Input: nums = [1], k = 1
     * Output: [1]
     *</p>
     * Constraints:
     *<p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * k is in the range [1, the number of unique elements in the array].
     * It is guaranteed that the answer is unique.
     *<p>
     * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     */
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Integer> queue;
        Integer[] array;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, (map.get(num) + 1));
            }
        }

        for (Integer integer : map.keySet()) {

        }
        array = map.values().toArray(new Integer[0]);
        Arrays.sort(array, Comparator.reverseOrder());
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
