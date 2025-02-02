package com.leetcode.linked.list;


public class CopyListWithRandomPointer {
    // A -> A` -> B -> C -> D ->
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val); // set the value for A`
            copy.next = current.next; // A` -> B
            current.next = copy; // A -> A` -> B
            current = copy.next; // B
        }

        // A -> A` -> B -> B` -> C -> C` -> D -> D`
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        Node newHead = head.next;
        Node newCurrent = newHead;
        current = head;
        // A` -> B -> B` -> C -> C` -> D -> D`
        while (newCurrent.next != null) {
            current.next = newCurrent.next; // A -> B -> B` -> C -> C` -> D -> D`
            current = current.next; // B -> B` -> C -> C` -> D -> D`
            newCurrent.next = newCurrent.next.next; // A` -> B` -> C -> C` -> D -> D`
            newCurrent = newCurrent.next; // B` -> C -> C` -> D -> D`
        }
        current.next = null; // A -> B -> C -> D
        // A` -> B` -> C -> C` -> D -> D`
        // B` -> C -> C` -> D -> D`
        return newHead;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);
        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        Node temp = head;
        while (temp != null) {
            System.out.print("[" + temp.val + " " + temp + ", r "+ (temp.random != null ? temp.random.val : null) + " " + temp.random + "] -> ");
            temp = temp.next;
        }
        CopyListWithRandomPointer copyListWithRandomPointer = new CopyListWithRandomPointer();
        Node result = copyListWithRandomPointer.copyRandomList(head);
        temp = result;
        System.out.println();
        while (temp != null) {
            System.out.print("[" + temp.val + " " + temp + ", r "+ (temp.random != null ? temp.random.val : null) + " " + temp.random + "] -> ");
            temp = temp.next;
        }

        temp = head;
        System.out.println();
        while (temp != null) {
            System.out.print("[" + temp.val + " " + temp + ", r "+ (temp.random != null ? temp.random.val : null) + " " + temp.random + "] -> ");
            temp = temp.next;
        }
    }

 }
