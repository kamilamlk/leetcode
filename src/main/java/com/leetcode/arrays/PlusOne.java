package com.leetcode.arrays;

import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        var needExtra = plusOne(digits, digits.length - 1);
        if (needExtra) {
            var newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }

    private boolean plusOne(int[] digits, int index) {
        if (index < 0) {
            return true;
        }
        if (digits[index] == 9) {
            digits[index] = 0;
            return plusOne(digits, index - 1);
        } else {
            digits[index] = digits[index] + 1;
            return false;
        }
    }

    public static void main(String[] args) {
        int[] digits = {1, 2, 3};
        PlusOne plusOne = new PlusOne();
        int[] result = plusOne.plusOne(digits);
        System.out.println(Arrays.toString(result));

        int[] digits2 = {4, 3, 2, 1};
        result = plusOne.plusOne(digits2);
        System.out.println(Arrays.toString(result));

        int[] digits3 = {9, 9, 9};
        result = plusOne.plusOne(digits3);
        System.out.println(Arrays.toString(result));
    }
}
