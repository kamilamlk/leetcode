package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        StringBuilder builder = new StringBuilder();
        builder.delete(builder.length() - 1, builder.length());
        List<String> strings = new ArrayList<>();
        track('(', n - 1, n, builder, strings);
        return strings;
    }

    private void track(char c, int open, int close, StringBuilder builder, List<String> array) {
        if(open < 0 || close < 0) {
            return;
        }
        if(open == 0 && close == 0) {
            System.out.println("Final: " + builder + " " + open + " " + close);
            array.add(builder.toString());
            return;
        }
        builder.append(c).reverse();
        System.out.println(builder + " " + open + " " + close);
        track('(', open - 1, close, builder, array);
        builder.deleteCharAt(builder.length() - 1);
        track(')', open, close - 1, builder, array);
    }

    public static void main(String[] args) {
        GenerateParentheses parentheses = new GenerateParentheses();
        System.out.println(parentheses.generateParenthesis(3));
    }
}
