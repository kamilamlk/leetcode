package com.leetcode.dynamic;

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int b2 = 1;
        int b1 = 2;
        int b = 0;
        for (int i = 3; i <= n; i++) {
            b = b1 + b2;
            b2 = b1;
            b1 = b;
        }
        return b;
    }


    public static void main(String[] args) {
        ClimbingStairs stairs = new ClimbingStairs();
        System.out.println(stairs.climbStairs(4));
    }
}
