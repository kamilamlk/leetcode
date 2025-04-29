package com.leetcode.ocp.records;

public record CD(int a) {
    public static int b = 1;
    public static final int c = 2;
    public CD {
        System.out.println("Canonical: CD(int a)");
    }

//    public CD {
//        System.out.println("Compact: CD(int a)");
//        if (a > 3) {
//            throw new IllegalArgumentException("a > 3");
//        }
//    }

    CD(int a, int b) {
        this(a);

        System.out.println("Custom: CD(int a, int b)");
    }

    public void show() throws InterruptedException {
        System.out.println("show");
    }

    @Override
    public int a() {
        return a;
    }
}
