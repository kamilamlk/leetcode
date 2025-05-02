package com.leetcode.stack;

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
    }

    private int calculate(String s, int i) {

        int left = 0;
        char c = '+';
        while(i < s.length() && s.charAt(i) != ')') {
            // skill space
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }
            // set operator
            if (isOperator(s.charAt(c))) {
                c = s.charAt(i);
                i++;
                continue;
            }
            int j = i;

            while (j < s.length() && isDigit(s.charAt(j))) {
                j++;
            }
            int right = Integer.parseInt(s.substring(i, j));
            if (c == '+') {
                left += right;
            } else if (c == '-') {
                left -= right;
            }
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-';
    }
}
