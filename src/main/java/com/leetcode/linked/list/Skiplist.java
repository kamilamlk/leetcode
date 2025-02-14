package com.leetcode.linked.list;

// TODO undone
public class Skiplist {
    private SkipListNode head;
    public int level = 0;

    private static class SkipListNode {
        int val;
        SkipListNode next;
        SkipListNode down;
        SkipListNode up;
        SkipListNode left;

        public SkipListNode(int val) {
            this.val = val;
        }
    }

    public Skiplist() {

    }

    public boolean search(int target) {
        return false;
    }

    public void add(int num) {
        SkipListNode current = head;
        // find where to insert
        while (current.down != null) {
            while (current.next != null && current.next.val <= num) {
                current = current.next;
            }
        }
        SkipListNode node = put(num, current);
        // do backtracking to insert into upper levels if needed
        while (shouldInsert()) {
            while (current.left != null && current.up == null) {
                current = current.left;
            }
            // if there is nothing on the left side and up side, I need to create a new head
            if (current.left == null && current.up == null) {
                SkipListNode newHead = new SkipListNode(Integer.MIN_VALUE);
                newHead.down = current;
                current = newHead;
                head = newHead;
            } else {
                current = current.up;
            }
            SkipListNode down = node;
            node = put(num, current);
            node.down = down;
            down.up = node;
        }
    }

    private boolean shouldInsert() {
        return (Math.random() % 2) < 0.5;
    }

    private SkipListNode put(int num, SkipListNode leftNode) {
        SkipListNode node = new SkipListNode(num);
        if (leftNode.val > num) {
            throw new RuntimeException("Invalid input");
        }

        node.left = leftNode;
        node.next = leftNode.next;
        leftNode.next = node;
        if (node.next != null) {
            node.next.left = node;
        }
        return node;
    }

    public boolean erase(int num) {
        // find num node;
        SkipListNode current = head;
        // track to remove upper levels if needed
        boolean found = false;
        while (current != null) {
            if (current.val == num) {
                found = true;
                SkipListNode next = current.next;
                SkipListNode left = current.left;
                left.next = next;
                if (next != null) {
                    next.left = left;
                }
            }
            current = current.up;
        }
        return found;
    }

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.add(4);
        skiplist.add(5);
        skiplist.add(6);
        skiplist.add(7);
    }
}
