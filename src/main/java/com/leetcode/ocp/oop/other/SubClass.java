package com.leetcode.ocp.oop.other;

public class SubClass extends SuperClass {

    SubClass() {
        super();
    }

    @Override
    public void someMethod(int a) {
        System.out.println("SubClass someMethod");
        someMethod("s: " + a);
    }

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.someMethod("1");
        System.out.println("SubClass");
    }
}
