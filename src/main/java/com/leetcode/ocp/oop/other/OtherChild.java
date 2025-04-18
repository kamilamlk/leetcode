package com.leetcode.ocp.oop.other;

import com.leetcode.ocp.oop.inheritance.Parent;

public class OtherChild extends Parent {
    public OtherChild(int anInt, String aString) {
        super(anInt, aString);
    }

    void somMethod(final int a) {
        System.out.println("Parent somMethod");
    }
}
