package com.leetcode.linked.list;

public class Skiplist {
    private int level;
    SkipListNode root;

    public Skiplist() {
        root = new SkipListNode(-1);
    }

    public boolean search(int target) {

    }

    public void add(int num) {

    }

    public boolean erase(int num) {

    }

    private boolean shouldPromoteDown() {
        return Math.random() % 2 < 0.5;
    }

    private static class SkipListNode {
        int val;
        SkipListNode next;
        SkipListNode down;

        public SkipListNode(int val) {
            this.val = val;
        }
    }
}
