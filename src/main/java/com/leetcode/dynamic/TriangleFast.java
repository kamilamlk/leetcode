package com.leetcode.dynamic;


import java.util.List;

public class TriangleFast {
    public int minimumTotal(List<List<Integer>> triangle) {
        return getMin(triangle, 0, 0);
    }

    private int getMin(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }
        return Math.min(getMin(triangle, i + 1, j), getMin(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }

    public static void main(String[] args) {
        TriangleFast triangle = new TriangleFast();
        List<List<Integer>> triangleList = List.of(
            List.of(2),
            List.of(3, 4),
            List.of(6, 5, 7),
            List.of(4, 1, 8, 3)
        );
        System.out.println(triangle.minimumTotal(triangleList));

        List<List<Integer>> shortTriangle = List.of(
            List.of(-10)
        );
        System.out.println(triangle.minimumTotal(shortTriangle));
    }

}
