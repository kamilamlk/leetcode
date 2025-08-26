package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        int[] products = new int[num1.length() + num2.length()];
        for(int i = num1.length() - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for(int j = num2.length() - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }
        int carry = 0;

        for(int i = products.length - 1; i>= 0; i--) {
            int temp = products[i] + carry;
            carry = temp / 10;
            products[i] = temp % 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        // remove leading zeros
        while (!sb.isEmpty() && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.isEmpty() ? "0" : sb.toString();
    }
    public static void main(String[] args) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("123", "456")); // Expected: 56088
        //System.out.println(multiplyStrings.multiply("2", "3")); // Expected: 6
        //System.out.println(multiplyStrings.multiply("0", "12345")); // Expected: 0
        //System.out.println(multiplyStrings.multiply("12345", "0")); // Expected: 0
    }
}
