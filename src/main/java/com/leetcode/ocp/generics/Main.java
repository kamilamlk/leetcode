package com.leetcode.ocp.generics;

public class Main {
    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(1, null); // treats as a new type
        Node<String> node2 = new Node<>("Hello", null); // treats as a new type
        find(new Node<>("null", null));
        var node3 = new Node<>(null, null); // var infers type as Node<Integer>
        System.out.println(node1.getClass());
        System.out.println(node2.getClass());
    }

    static void find(Node<String> node) {
        // This method can only accept Node<String>
        System.out.println("Node data: " + node.getData());
    }
}
