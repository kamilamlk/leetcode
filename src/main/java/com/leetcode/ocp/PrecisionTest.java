package com.leetcode.ocp;

public class PrecisionTest {
    public static void main(String[] args) {
        double a = 1.0 - 2.0/3.0;
        double b = 1.0 / 3.0;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a == b: " + (a == b)); // false
        System.out.println("Difference: " + Math.abs(a - b)); // small non-zero

        double epsilon = 1e-10;

        if (Math.abs(a - b) < epsilon) {
            System.out.println("Effectively equal ✅");
        } else {
            System.out.println("Not equal ❌");
        }

        System.out.println("Binary x: " + Long.toBinaryString(Double.doubleToRawLongBits(a)));
        System.out.println("Binary y: " + Long.toBinaryString(Double.doubleToRawLongBits(b)));
    }
}