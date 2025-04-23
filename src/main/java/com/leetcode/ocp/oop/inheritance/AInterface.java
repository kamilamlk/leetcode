package com.leetcode.ocp.oop.inheritance;

public interface AInterface {
    int A = 0;
    String B = "A";
    default void run() {
        System.out.println("AInterface");
    }

    private void doSomething(int a) {
        System.out.println(A);
    }
}
