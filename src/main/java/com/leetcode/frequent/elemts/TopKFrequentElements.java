package com.leetcode.frequent.elemts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int n : count.keySet()) {
            int c = count.get(n);
            if (bucket[c] == null) bucket[c] = new ArrayList<>();
            bucket[c].add(n);
        }

        int[] res = new int[k];
        int j = 0;
        for (int i = nums.length; i >= 0; i--) {
            if (bucket[i] == null) continue;
            for (int n : bucket[i]) {
                res[j++] = n;
                if (j == k) return res;
            }
        }
        return res;
    }
}
