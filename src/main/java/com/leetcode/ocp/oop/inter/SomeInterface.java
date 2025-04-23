package com.leetcode.ocp.oop.inter;

public interface SomeInterface {
    int wheels = 6;

    private static int getNumOfCylinders() {
        return 4;
    }

    default int getEngineSize() {
        return 4;
    }

    void g();
    void h();
}
