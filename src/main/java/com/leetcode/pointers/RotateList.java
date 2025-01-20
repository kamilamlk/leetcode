package com.leetcode.pointers;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int size = getSize(head);
        int left = size - k % size;
        ListNode current = head;
        for (int i = 0; i < left - 1; i++) {
            current = current.next;
        }
        if (current != null) {
            // normalize
            ListNode temp = current;
            current = current.next;
            temp.next = null;
            if (current != null) {
                ListNode newHead = current;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = head;
                return newHead;
            } else {
                return head;
            }
        } else {
            // TODO
            return head;
        }
    }
    
    private int getSize(ListNode node) {
        if (node == null) {
            return 0;
        }
        return getSize(node.next) + 1;
    }

    public static void main(String[] args) {
        ListNode node = toListNode(1, 2);
        RotateList reverse = new RotateList();
        ListNode result = reverse.rotateRight(node, 2);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }

    private static ListNode toListNode(int... vars) {
        ListNode head = new ListNode();
        ListNode current = head;
        for (int var : vars) {
            current.next = new ListNode(var);
            current = current.next;
        }
        return head.next;
    }
}
