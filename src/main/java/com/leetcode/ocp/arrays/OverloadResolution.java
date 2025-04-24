package com.leetcode.ocp.arrays;

public class OverloadResolution {
    private static void flipFlop(String str, int i, Integer iRef) {
        System.out.println(str + " ==> String, int, Integer");
    }

    private static void flipFlop(String str, int i, int j) {
        System.out.println(str + " ==> String, Integer, int");
    }

    private static void flipFlop(String str, Integer iRef, int i) {
        System.out.println(str + " ==> String, Integer, int");
    }


    boolean testIfOn(Light light) {
        return true;
    }

    boolean testIfOn(TubeLight tubeLight) {
        return false;
    }

    public static void main(String[] args) {
        String str = "Hello";
        int i = 1;
        Integer iRef = 2;

        flipFlop(str, i, iRef); // String, int, Integer
        flipFlop(str, iRef, i); // String, Integer, int
        //flipFlop(str, iRef, iRef); // String, Integer, Integer ambiguous
        flipFlop(str, iRef, 3); // String, Integer, int
        flipFlop(str, 3, iRef); // String, int, Integer

        TubeLight tubeLight = new TubeLight();
        Light light = new Light();
        Light light2 = new TubeLight();

        OverloadResolution or = new OverloadResolution();
        System.out.println(or.testIfOn(tubeLight)); // false
        System.out.println(or.testIfOn(light)); // true
        System.out.println(or.testIfOn(light2)); // true
    }
}
