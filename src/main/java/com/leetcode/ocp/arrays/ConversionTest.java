package com.leetcode.ocp.arrays;

public class ConversionTest {

    public static void printType(long x) {
        System.out.println("long primitive");
    }

    public static void printType(Long x) {
        System.out.println("Long wrapper");
    }

    public static void printType(Object x) {
        System.out.println("Object");
    }

    public static void main(String[] args) {
        int a = 10;
        Integer b = 20;
        Long c = 30L;

        printType(a);  // Line A  long primitive / widening
        printType(b);  // Line B  unboxing + primitive widening
        printType(c);  // Line C long wrapper / nothing

        printType((long) b);       // Line D long primitive / unboxing + primitive widening
        printType((Object) b);     // Line E object / upcasting
        printType((Long) (long) a); // Line F widening + boxing
    }
}