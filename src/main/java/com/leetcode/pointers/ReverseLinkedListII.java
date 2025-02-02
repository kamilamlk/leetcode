package com.leetcode.pointers;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head;
        int i = 1;
        while (i < left - 1 && current != null) {
            current = current.next;
            i++;
        }
        if (current == null) {
            return null;
        }
        ListNode prev = current;

        current = current.next;
        prev.next = reverse(current.next, current, i + 1, right, current);

        return head;
    }

    private ListNode reverse(ListNode node, ListNode prev, int i, int right, ListNode tail) {
        if (node == null) {
            return prev;
        }
        if (i == right - 1) {
            tail.next = node.next;
            node.next = prev;
            return node;
        }
        ListNode next = node.next;
        node.next = prev;
        prev = node;
        node = next;
        return reverse(node,prev, i + 1, right, tail);
    }

    public static void main(String[] args) {
        ListNode node = toListNode(5);
        ReverseLinkedListII reverse = new ReverseLinkedListII();
        ListNode result = reverse.reverseBetween(node, 1, 1);
        System.out.println(result);
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
