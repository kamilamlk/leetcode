package com.leetcode.arrays;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int[][] heights = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                heights[i][j] = countHeight(matrix, i, j);
            }
        }
        int max = 0;
        for(int i = 0; i < matrix.length; i++) {
            max = Math.max(max, largestRectangleInHistogram(heights[i]));
        }
        return max;
    }

    private int countHeight(char[][] matrix, int i, int j) {
        int height = 0;
        while(i < matrix.length && matrix[i][j] == '1') {
            i++;
            height++;
        }
        return height;
    }


    private int largestRectangleInHistogram(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int h = (i == n ? 0 : heights[i]);
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaximalRectangle maximalRectangle = new MaximalRectangle();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalRectangle.maximalRectangle(matrix)); // Output: 6
    }
}
