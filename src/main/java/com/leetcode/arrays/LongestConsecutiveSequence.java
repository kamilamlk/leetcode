package com.leetcode.arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LongestConsecutiveSequence {


    public static void main(String[] args) {

        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
//        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
//        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{0}));
//        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{}));
    }

    int max = 0;

    private int longestConsecutive(int[] nums) {
        Map<Integer, LinkedList<Integer>> ascendingMap = new HashMap<>();
        Map<Integer, LinkedList<Integer>> descendingMap = new HashMap<>();
        for (int num : nums) {
            find(num, ascendingMap, descendingMap);
            union(num, ascendingMap, descendingMap);
        }
        return max;
    }

    private void find(int num,
                      Map<Integer, LinkedList<Integer>> ascendingMap,
                      Map<Integer, LinkedList<Integer>> descendingMap) {
        if (ascendingMap.containsKey(num) || descendingMap.containsKey(num)) {
            return;
        }
        if (!ascendingMap.containsKey(num + 1)) {
            LinkedList<Integer> list = new LinkedList<>();
            list.addFirst(num);
            ascendingMap.put(num, list);
        } else {
            LinkedList<Integer> list = ascendingMap.get(num + 1);
            ascendingMap.remove(num + 1);
            list.addFirst(num);
            ascendingMap.put(num, list);
        }

        if (!descendingMap.containsKey(num - 1)) {
            LinkedList<Integer> list = new LinkedList<>();
            list.addFirst(num);
            descendingMap.put(num, list);
        } else {
            LinkedList<Integer> list = descendingMap.get(num - 1);
            descendingMap.remove(num - 1 );
            list.addFirst(num);
            descendingMap.put(num, list);
        }
    }

    private void union(int num,
                       Map<Integer, LinkedList<Integer>> ascendingMap,
                       Map<Integer, LinkedList<Integer>> descendingMap) {
        LinkedList<Integer> ascendingList = ascendingMap.get(num);
        LinkedList<Integer> descendingList = descendingMap.get(num);

        int last = ascendingList.getLast();
        if (descendingMap.containsKey(last)) {
            LinkedList<Integer> list = descendingMap.get(last);
            list.addAll(descendingList);
            descendingMap.remove(num);
            max = Math.max(max, list.size());
        }
        last = descendingList.getLast();
        if (ascendingMap.containsKey(last)) {
            LinkedList<Integer> list = ascendingMap.get(last);
            list.addAll(ascendingList);
            ascendingMap.remove(num);
            max = Math.max(max, list.size());
        }

        max = Math.max(max, Math.max(ascendingList.size(), descendingList.size()));
    }
}
