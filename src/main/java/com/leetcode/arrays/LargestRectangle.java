package com.leetcode.arrays;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangle {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<> ();
        int maxArea = 0;
        for(int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangle largestRectangle = new LargestRectangle();
        int[] heights = {1,3,0,2,3,2};
        System.out.println(largestRectangle.largestRectangleArea(heights)); // Output: 10
    }
}
