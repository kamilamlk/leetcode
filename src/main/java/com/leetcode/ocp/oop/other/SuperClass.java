package com.leetcode.ocp.oop.other;

public abstract class SuperClass {

    public abstract void someMethod(final int a);

    public final void someMethod(final String a) {
        System.out.println("SuperClass someMethod: ");
    }
}
