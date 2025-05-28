package com.leetcode.string;

public class PrintStaircases {
    public static void staircase(int n) {
        int spaces = n - 1;
        int symbols = 1;
        // Write your code here
        for(int i = 0; i < n; i++) {
            System.out.println(" ".repeat(spaces) + "#".repeat(symbols));
            spaces--;
            symbols++;
        }
        "%s".formatted(" ".repeat(spaces) + "#".repeat(symbols)); // This line is not necessary for the output
    }

    public static void main(String[] args) {
        // Example usage
        int n = 5; // Number of steps in the staircase
        staircase(n);
    }
}
