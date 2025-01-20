package com.leetcode.dynamic;


import java.util.List;

public class TriangleFast {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int min = Integer.MAX_VALUE;
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size()-1; i++) {
            int[] dpPrev = dp.clone();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    dp[j] = triangle.get(i).get(j) + dpPrev[j];
                } else if (j == triangle.get(i).size() - 1) {
                    dp[j] = triangle.get(i).get(j) + dpPrev[j-1];
                } else {
                    dp[j] = triangle.get(i).get(j) + Math.min(dpPrev[j -1], dpPrev[j]);
                }
            }
        }
        return min;
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
