package com.leetcode.ocp.api;

import java.util.Comparator;

public class AnonymousClass {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            {
                System.out.println("Anonymous class instance created");
            }

            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };

        System.out.println(comparator.compare(1, 2)); // Output: 0
    }
}
