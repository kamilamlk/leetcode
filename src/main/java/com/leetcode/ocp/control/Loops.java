package com.leetcode.ocp.control;

import java.util.List;

public class Loops {
    public static void main(String[] args) {
        int sum = 0;
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        for (Integer i : list) {
            sum += i;
        }
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == j) continue outer;
                System.out.println("End i: " + i + ", j: " + j);
            }
        }

    }
}
