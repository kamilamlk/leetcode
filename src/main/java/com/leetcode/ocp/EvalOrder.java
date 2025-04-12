package com.leetcode.ocp;

public class EvalOrder {
    public static void main(String[] args) {
        int j = 2;
        add(eval(j++, " + "), eval(j++, " * "), eval(j, "\n"));
    }

    public static int eval(int operand, String str) {
        System.out.print(operand + str);
        return operand;
    }

    public static void add(int operand1, int operand2, int operand3) {
        System.out.println(operand1 + operand2 + operand3);
    }
}
