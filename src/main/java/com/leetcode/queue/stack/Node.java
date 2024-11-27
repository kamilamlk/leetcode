package com.leetcode.queue.stack;

import java.util.Arrays;
import java.util.List;

public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new java.util.ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new java.util.ArrayList<>();
    }

    public Node(int val, List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        return val + " ";
    }
}
