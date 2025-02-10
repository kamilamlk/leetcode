package com.leetcode.linked.list;

public class Skiplist {
    private int level;
    SkipListNode root;


    public Skiplist() {
        root = new SkipListNode(-1);
        root.down = new SkipListNode(-1);
        level = 1;
    }

    public boolean search(int target) {
        return find(target) != null;
    }

    public void add(int num) {
        SkipListNode current = root;
        SkipListNode prev = null;
        // find the node to insert
        while (current != null) {
            while (current.left != null && current.left.val < num) {
                current = current.left;
            }
            prev = current;
            current = current.down;
        }

        SkipListNode newNode = new SkipListNode(num);
        newNode.left = prev.left;
        newNode.right = prev.right;
        prev.right = newNode;

    }

    private SkipListNode find(int num) {
        SkipListNode current = root;
        while (current.down != null) {
            while (current.left != null && current.left.val <= num) {
                current = current.left;
            }
            current = current.down;
        }

        while (current.left != null && current.left.val <= num) {
            current = current.left;
        }

        if (current.val == num) {
            return current;
        } else {
            return null;
        }

    }

    public boolean erase(int num) {
        SkipListNode node = find(num);

        if (node == null) {
            return false;
        }
        SkipListNode current = node;
        while (current != null) {
            current.left.right = current.right;
            current.right.left = current.left;
            current = current.up;
        }

        return true;
    }

    private boolean shouldPromoteUp() {
        return Math.random() % 2 < 0.5;
    }

    private static class SkipListNode {
        int val;
        SkipListNode left;
        SkipListNode right;
        SkipListNode up;
        SkipListNode down;

        public SkipListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(0)); // false
        skiplist.add(4);
        System.out.println(skiplist.search(1)); // true
        System.out.println(skiplist.erase(0)); // false
        System.out.println(skiplist.erase(1)); // true
        System.out.println(skiplist.search(1)); // false
    }
}
