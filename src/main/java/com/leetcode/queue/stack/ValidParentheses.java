package com.leetcode.queue.stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        char[] stack = new char[chars.length];
        if (chars.length == 1) {
            return false;
        }
        int pair = 0;
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack[pair++] = c;
            } else {
                if (pair == 0) {
                    return false;
                }
                char top = stack[--pair];
                if (!isPair(c, top)) {
                    return false;
                }
            }
        }
        return pair == 0;
    }

    private boolean isPair(char end, char start) {
        if (end == ')' && start == '(') {
            return true;
        }
        if (end == ']' && start == '[') {
            return true;
        }
        return end == '}' && start == '{';
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("()"));
        System.out.println(validParentheses.isValid("()[]{}"));
        System.out.println(validParentheses.isValid("(]"));
        System.out.println(validParentheses.isValid("([)]"));
        System.out.println(validParentheses.isValid("{[]}"));
        System.out.println(validParentheses.isValid("){"));
    }
}
