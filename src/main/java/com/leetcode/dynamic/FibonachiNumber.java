package com.leetcode.dynamic;

public class FibonachiNumber {
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int f = 0, f1 = 1, f2 = 0;
        for (int i = 2; i <= n; i++) {
            f = f1 + f2;
            f2 = f1;
            f1 = f;
        }
        return f;
    }

    public static void main(String[] args) {
        FibonachiNumber fibonachiNumber = new FibonachiNumber();
        System.out.println(fibonachiNumber.fib(55));
    }
}
