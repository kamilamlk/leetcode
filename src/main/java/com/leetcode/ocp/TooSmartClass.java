package com.leetcode.ocp;

public class TooSmartClass {
    public TooSmartClass() {
        someMethod();
    }

    public void someMethod() {

    }
    public static void main(String[] args) {
        double weight = 1.6d, thePrice;
        if (weight > 10.0) thePrice = 10;
        if (weight < 5.0) thePrice = 5;
        else if (weight >= 5) thePrice = 6;
        else {
            thePrice = 0;
        }
        System.out.println(thePrice);

        final boolean b = true;
        int x;
        if (b) x = 1;
        if (!b) x = 2;
        System.out.println(x);

        TooSmartClass tooSmartClass = new TooSmartClass();
    }

    void fly() {

    }

    int fly(int a, int b) {
        return a * b;
    }
}
