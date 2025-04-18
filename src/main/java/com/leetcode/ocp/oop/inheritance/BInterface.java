package com.leetcode.ocp.oop.inheritance;

public interface BInterface {
    default void run() {
        System.out.println("BInterface");
    }
}
