package com.leetcode.linked.list;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectNodes {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connectNodes(new LinkedList<>(List.of(root)));
        return root;
    }

    private void connectNodes(Queue<Node> nodes) {

        while(!nodes.isEmpty()) {
            int size = nodes.size();
            Node prev = null;
            while (!nodes.isEmpty() && size > 0) {
                Node current = nodes.poll();
                if (prev != null) {
                    prev.next = current;
                }
                if (current.left != null) {
                    nodes.add(current.left);
                }
                if (current.right != null) {
                    nodes.add(current.right);
                }
                prev = current;
                size--;
            }
        }
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left,
                    Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static void main(String[] args) {
        ConnectNodes cn = new  ConnectNodes();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.right = new Node(7);

        cn.connect(root);
        System.out.println(root);
    }
}
