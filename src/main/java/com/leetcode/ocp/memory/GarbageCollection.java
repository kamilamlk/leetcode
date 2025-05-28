package com.leetcode.ocp.memory;

public class GarbageCollection {
    public static void main(String[] args) {
        var freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory before creating objects: " + freeMemory);
        for (int i = 0; i < 100000; i++) {
            new Object();
        }
        System.out.println("Free memory after creating objects: " + Runtime.getRuntime().freeMemory());

        System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
        System.out.println("Max memory: " + Runtime.getRuntime().maxMemory());
        System.out.println("Used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
    }
}
