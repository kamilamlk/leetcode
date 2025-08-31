package com.leetcode.arrays;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums) {
        int[] result = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>(); // monotonic decreasing stack
        for(int i = nums.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peekLast() <= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.push(nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        NextGreaterElement nextGreaterElement = new NextGreaterElement();
        System.out.println(
            Arrays.toString(nextGreaterElement.nextGreaterElement(new int[] { 2, 7, 4, 3, 5 }))
        );
    }
}
