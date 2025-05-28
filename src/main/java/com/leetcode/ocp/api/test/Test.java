package com.leetcode.ocp.api.test;

public class Test {
    private char x = '=';

    public static void main(String[] args) {
        char x = '<';

        Test t = new Test() {
            private char x = '>';

            public String toString() {
                return this.x + super.toString() + x;
            }
        };

        System.out.println(t); // Output: >42<
    }
    public String toString() {
        return x + "42";
    }
}
