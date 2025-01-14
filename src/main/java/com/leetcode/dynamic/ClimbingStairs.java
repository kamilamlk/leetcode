package com.leetcode.dynamic;

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n < 3) {
            return 1;
        }
        int s1 = 1;
        int s2 = 2;
        int s = 0;
        for (int i = 2; i < n; i++) {
            s = s1 + s2;
            s1 = s2;
            s2 = s;
        }
        return s;
    }


    public static void main(String[] args) {
        ClimbingStairs stairs = new ClimbingStairs();
        System.out.println(stairs.climbStairs(4));
    }
}
