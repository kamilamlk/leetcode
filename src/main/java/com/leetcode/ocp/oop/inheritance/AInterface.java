package com.leetcode.ocp.oop.inheritance;

public interface AInterface {
    default void run() {
        System.out.println("AInterface");
    }
}
