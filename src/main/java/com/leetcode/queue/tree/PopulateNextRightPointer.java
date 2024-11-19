package com.leetcode.queue.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// TODO Improve the solution
public class PopulateNextRightPointer {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connectNodes(new LinkedList<>(List.of(root)));
        return root;
    }

    private void connectNodes(Queue<Node> nodes) {
        if (nodes.isEmpty()) {
            return;
        }
        Queue<Node> next = new LinkedList<>();
        Node current = nodes.poll();
        if (current.left != null) {
            next.add(current.left);
        }
        if (current.right != null) {
            next.add(current.right);
        }

        while (!nodes.isEmpty()) {
            current.next = nodes.poll();
            current = current.next;
            if (current.left != null) {
                next.add(current.left);
            }
            if (current.right != null) {
                next.add(current.right);
            }

        }
        connectNodes(next);
    }

    public static void main(String[] args) {
        PopulateNextRightPointer populateNextRightPointer = new PopulateNextRightPointer();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        var result = populateNextRightPointer.connect(root);
        System.out.println(result);
    }
}
