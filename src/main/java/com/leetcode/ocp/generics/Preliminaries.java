package com.leetcode.ocp.generics;

public class Preliminaries {
    public static void main(String[] args) {
        Node<Integer> intNode = new Node<>(42, null);
        Node rawNode = intNode; // raw type, no type safety
        intNode = null; // This is allowed, but unsafe
        Node<String> stringNode = new Node<>("Hello", null);
        // rawNode can hold any type of Node, but we lose type safety
        System.out.println("Raw Node data: " + rawNode.getData());
        rawNode.setData("World"); // This is unsafe, but allowed
        rawNode.type();
        intNode = rawNode; // This is allowed, but unsafe
        intNode.type();

        int iRef = intNode.getData();
    }
}
