package com.leetcode.ocp.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    static char c;
    public static void main(String[] args) {
        Outer.Inner inner = new Outer.Inner();
        inner.display();

        Outer outer = new Outer();
        Outer.NonStaticInner nonStaticInner =  outer.new NonStaticInner();
        nonStaticInner.display();
        outer.instanceField = 30; // Accessing instance field
        outer.new NonStaticInner().display(); // Accessing non-static inner class
        nonStaticInner.display();

        System.out.println(c);
    }
}
