package com.leetcode.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MinArrows {
    public int findMinArrowShots(int[][] points) {
        int length = points.length;
        Arrays.sort(points, Comparator.comparing(n -> n[0]));

        int[] covers = new int[points.length];

        for(int i = 0; i < points.length; i++) {
            covers[i] = countCovers(points, i);
        }
        Arrays.sort(covers);
        int count = 0;
        int i = covers.length - 1;

        while(i >= 0 && length > 0) {
            length -= covers[i--];
            count++;
        }
        return count;
    }

    private int countCovers(int[][] points, int i) {
        int end = points[i][1];
        int count = 0;
        for (; i < points.length; i++) {
            if (points[i][0] > end) {
                return count;
            }
            end = Math.min(end, points[i][1]);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        MinArrows minArrows = new MinArrows();
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(minArrows.findMinArrowShots(points)); // Output: 2
        System.out.println(minArrows.findMinArrowShots(new int[][]{{1,2},{3,4},{5,6},{7,8}})); // Output: 4
        System.out.println(minArrows.findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}})); // Output: 2
        System.out.println(minArrows.findMinArrowShots(new int[][]{{1,2147483647}})); // Output: 1

        System.out.println(minArrows.findMinArrowShots(new int[][]{{3, 9}, {7, 12}, {3, 8}, {6,8}, {9,10}, {2,9}, {0,9},{3,9},{0,6}, {2,8}})); // Output: 2
    }
}
