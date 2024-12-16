package com.leetcode.dynamic;

public class Tribonacci {
    public int tribonacci(int n) {
        if (n < 2) {
            return n;
        } else if (n == 2) {
            return 1;
        }

        int t = 0;
        int t1 = 1, t2 = 1, t3 = 0;
        for (int i = 3; i <= n; i++) {
            t = t1 + t2 + t3;
            t3 = t2;
            t2 = t1;
            t1 = t;
        }
        return t;
    }

    public static void main(String[] args) {
        Tribonacci tribonacci = new Tribonacci();
        System.out.println(tribonacci.tribonacci(25));
    }
}
