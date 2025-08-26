package com.leetcode.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] numObjects = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numObjects[i] = String.valueOf(nums[i]);
        }

        // Сортируем массив с помощью компаратора
        Arrays.sort(numObjects, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for(String num: numObjects) {
            sb.append(num);
        }
        return sb.toString();
    }
}
