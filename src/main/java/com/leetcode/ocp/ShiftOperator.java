package com.leetcode.ocp;

import java.util.Arrays;

public class ShiftOperator {
    public static void main(String[] args) {
        int a = 3;
        int shiftDistance = 33;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a << shiftDistance));

        a = -10;
        System.out.println(Integer.toBinaryString(a >> shiftDistance));
        System.out.println(a >> shiftDistance);

        int x = 5;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(x >>> 1); // Output: a large positive number
        System.out.println(Integer.toBinaryString(x >>> 1));

        System.out.println( 5 < 6 ? 5 : "Hi");

        System.out.println(5 % 2 * 4 / 2);
        int i = (+15);
        i = 20;
        int j = 30;
        Number number = i < j ? ++i : j * 10D;
        System.out.println(number.getClass());
        System.out.println(i == j ? i == j : "i not equal to j");
        int[] array = new int[] {1,2,3};
        i = 0;
        array[i] = i = 9;
        System.out.println(Arrays.toString(array));

        {
            System.out.println("Hi");
        }
    }
}
