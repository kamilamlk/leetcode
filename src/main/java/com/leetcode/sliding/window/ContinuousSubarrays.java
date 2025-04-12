package com.leetcode.sliding.window;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContinuousSubarrays {
    /*
    5,4,2,4


    5,4,2,5
    l = 0
    r = 2
    min 2
    max 0 1 2
     */
    public long continuousSubarrays(int[] nums) {
        int l = 0;
        long res = 0;  // Use long to avoid overflow
        Deque<Integer> minD = new LinkedList<>();
        Deque<Integer> maxD = new LinkedList<>();

        for(int r = 0; r < nums.length; r++) {
            // все что перед должно быть меньше
            while (!minD.isEmpty() && nums[minD.peekLast()] >= nums[r]) {
                minD.pollLast();
            }
            // все что перед должно быть больше
            while (!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[r]) {
                minD.pollLast();
            }
            minD.offerLast(r);
            maxD.offerLast(r);

            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > 2) {
                l++;
                if (minD.peekFirst() < l) minD.pollFirst();
                if (maxD.peekFirst() < l) maxD.pollFirst();
            }
            res += r - l + 1;
        }


        return res;
    }

    private int subArrays(int i, int j) {
        return j - i;
    }

    public static void main(String[] args) {
        ContinuousSubarrays continuousSubarrays = new ContinuousSubarrays();
        System.out.println(continuousSubarrays.continuousSubarrays(new int[]{5,4,2,4}));
//        System.out.println(continuousSubarrays.continuousSubarrays(new int[]{1, 2, 3, 4, 5, 6}));
//        System.out.println(continuousSubarrays.continuousSubarrays(new int[]{1, 2, 3, 4, 5, 6, 7}));
//        System.out.println(continuousSubarrays.continuousSubarrays(new int[]{1, 2, 3}));
    }
}
