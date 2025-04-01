package com.leetcode.math;

public class AverageSalary {
    public double average(int[] salary) {
        int min = salary[0];
        int max = 0;
        int sum = 0;
        for (int i : salary) {
            if (i < min) {
                min = i;
            } else if (i > max) {
                max = i;
            }
            sum += i;
        }
        int[] values = new int[] {1, 5, 10, 50, 100, 500, 1000};
        char[] symbs = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        return (sum - min - max) / (salary.length - 2.0);
    }
}
