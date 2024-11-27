package com.leetcode.queue.stack;

import java.util.Deque;

public class EvaluateRPN {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new java.util.LinkedList<>();
        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(calculate(a, b, token));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-");
    }

    private int calculate(int a, int b, String operator) {
        switch (operator) {
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "+":
                return a + b;
            case "-":
                return a - b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        EvaluateRPN rpn = new EvaluateRPN();
        System.out.println(rpn.evalRPN(new String[]{"2", "1", "+", "3", "*"}));

        System.out.println(rpn.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(rpn.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(rpn.evalRPN(new String[]{"10", "6", "9", "3", "/", "-", "*"}));
    }
}
