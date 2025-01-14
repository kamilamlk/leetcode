package com.leetcode.dynamic;

import java.util.Arrays;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m <= 1 || n <= 1) {
            return 1;
        }
        int[] dpPrev = new int[n];
        Arrays.fill(dpPrev, 1);

        int dpLeft = 1;
        for (int i = 1; i < m; i++) {
            dpLeft = 1;
            for (int j = 1; j < n; j++) {
                dpPrev[j-1] = dpLeft;
                dpLeft = dpLeft + dpPrev[j];
            }
            dpPrev[n-1] = dpLeft;
        }
        return dpLeft;
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 7));
    }
}
