package com.leetcode.stack;

import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    private final Map<Character, Character> brackets = Map.of(')', '(', '}', '{', ']', '[');

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == brackets.get(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
