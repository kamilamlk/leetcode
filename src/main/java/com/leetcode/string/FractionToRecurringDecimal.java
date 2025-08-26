package com.leetcode.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if(!((numerator < 0 && denominator < 0) ||(numerator >= 0 && denominator > 0))) {
            sb.append('-');
        }
        long num = Math.abs(numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        long remainder = num % den;
        if(remainder == 0) {
            return sb.toString();
        }
        sb.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while(remainder != 0) {
            if(map.containsKey(remainder)) {
                sb.insert(map.get(remainder), "(");
                sb.append(')');
                break;
            }
            map.put(remainder, sb.length()); // put index of first appearance
            remainder *= 10;
            sb.append(remainder / den);
            remainder %= den;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Deque<Integer> nums = new LinkedList<Integer>();
        Arrays.sort(new int[2]);

        FractionToRecurringDecimal fractionToRecurringDecimal = new FractionToRecurringDecimal();
        System.out.println(fractionToRecurringDecimal.fractionToDecimal(-1, -2147483648));

    }
}
