package com.leetcode.ocp.oop.inheritance;

public class Parent {
    protected int anInt;
    protected static String aString;

    public Parent(int anInt, String aString) {
        this.anInt = anInt;
        this.aString = aString;
    }

    static void staticMethod() {
        System.out.println("Parent static");
    }

    void instanceMethod() throws AbstractException{
        System.out.println("Parent instance");
    }

    void somMethod(final int a) {
        System.out.println("Parent somMethod");
    }

    void somMethod(int a, int b) {
        System.out.println("Parent somMethod %s, %s".formatted(a, b));
    }
}
