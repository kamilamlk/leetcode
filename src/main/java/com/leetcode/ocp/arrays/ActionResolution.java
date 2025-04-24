package com.leetcode.ocp.arrays;

public class ActionResolution {
    public void action(String str) {
        System.out.println(str + "=> (String)");
    }

    public void action(String str, int m) {
        System.out.println(str + "=> (String, int)");
    }

    public void action(String str, int m, int n) {
        System.out.println(str + "=> (String, int, int)");
    }

    public void action(String str, int... m) {
        System.out.println(str + "=> (String, int...)");
    }

    public void action(String str, Integer... m) {
        System.out.println(str + "=> (String, Integer...)");
    }

    public void action(String str, Number... m) {
        System.out.println(str + "=> (String, Number...)");
    }

    public void action(String str, Object... m) {
        System.out.println(str + "=> (String, Object...)");
    }

    public void action(Object obj) {
        System.out.println(obj + "=> (Object)");
    }

    public static void main(String[] args) {
        ActionResolution ar = new ActionResolution();
        ar.action("Hello");
        ar.action("Hello", 1);
        ar.action("Hello", 1, 2);
        //ar.action("Hello", 1, 2, 3);
        ar.action("Hello", new int[]{1, 2, 3});
        ar.action("Hello", new Integer[]{1, 2, 3});
        ar.action("Hello", new Number[]{1, 2, 3});
        ar.action("Hello", new Object[]{1.0, 2.0, 3.0});
    }
}
