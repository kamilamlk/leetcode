package com.leetcode.arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextSmallerElement {
    public int[] nextSmallerElement(int[] nums) {
        int[] result = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>(); // monotonic decreasing stack
        for(int i = nums.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop(); // remove from the top
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek(); // checks top
            stack.push(nums[i]); // add to the top
        }
        return result;
    }

    public static void main(String[] args) {
        NextSmallerElement nextSmallerElement = new NextSmallerElement();
        System.out.println(
            Arrays.toString(nextSmallerElement.nextSmallerElement(new int[] { 2, 7, 4, 3, 5 }))
        );
    }
}
