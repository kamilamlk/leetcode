package com.leetcode.stack;

import java.util.Stack;

public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(String token : tokens) {
            if(!isOperator(token)) {
                stack.push(Integer.valueOf(token));
            } else {
                if (stack.size() > 1) {
                    int result = calculate(stack.pop(), stack.pop(), token);
                    stack.push(result);
                }
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-");
    }

    private int calculate(int b, int a, String operator) {
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
        ReversePolishNotation rpn = new ReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*"};
//        System.out.println(rpn.evalRPN(tokens)); // Output: 9
//        tokens = new String[]{"4", "13", "5", "/", "+"};
//        System.out.println(rpn.evalRPN(tokens)); // Output: 6
        tokens = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(rpn.evalRPN(tokens)); // Output: 22
    }
}
