package com.leetcode.ocp.api;

public class TLCWithNSLClass {
    private int instanceField = 10;
    void nonStaticMethod(final int fp) { // (1) Non-static Method

        int flv = 10;
        final int hlv = 20;
        int nflv1 = 30;
        int nflv3;
        nflv1 = 40; // (6) Not effectively final local variable

        class NonstaticLocal {
            int f1 = fp;
            int f2 = flv; // (2) Effectively final local variable
            //int f3 = nflv3; // (3) Effectively final local variable
            // int f4 = nflv1; // (4) no Effectively final local variable
            // int f5 = nflv3; // (5) Not effectively final local variable
            int hlv;

            NonstaticLocal(int value) {
                hlv = value; // (7) Effectively final local variable
                System.out.println("NonstaticLocal constructor: " + hlv);
                System.out.println("Enclosing class instance field: " + TLCWithNSLClass.this.instanceField);
            }
        }

        NonstaticLocal nsl = new NonstaticLocal(200);
        int nflv2 = 50; // (8) Effectively final local variable
        nflv3 = 60;
        System.out.println("Local: "  + hlv);
    }

    public static void main(String[] args) {
        TLCWithNSLClass tlc = new TLCWithNSLClass();
        tlc.nonStaticMethod(100);

    }
}
