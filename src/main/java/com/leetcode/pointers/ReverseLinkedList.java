package com.leetcode.pointers;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return reverse(head, null);
    }

    private ListNode reverse(ListNode node, ListNode prev) {
        if (node == null) {
            return prev;
        }
        ListNode next = node.next;
        node.next = prev;
        prev = node;
        node = next;
        return reverse(node,prev);
    }

    public static void main(String[] args) {
        ListNode node = toListNode(1,2,3,4,5);
        ReverseLinkedList reverse = new ReverseLinkedList();
        ListNode result = reverse.reverseList(node);
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
