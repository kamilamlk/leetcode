package com.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

public class HappyNumber {
    public boolean isHappy(int n) {
        Map<Integer, Integer> circle = new HashMap<>();
        while(n != 1) {
            int sum = 0;
            while(n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            if (circle.getOrDefault(sum, 0) > 0) {
                return false;
            } else {
                circle.put(sum, 1);
                n = sum;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        System.out.println(happyNumber.isHappy(19)); // true
        System.out.println(happyNumber.isHappy(2)); // false
        System.out.println(happyNumber.isHappy(7)); // true
        System.out.println(happyNumber.isHappy(4)); // false
    }
}
