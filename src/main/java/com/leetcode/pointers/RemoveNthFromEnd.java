package com.leetcode.pointers;

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = getSize(head);
        int index = size - n - 1;
        if (head.next == null && n == 1) {
            return null;
        }

        if (index < 0) {
            head = head.next;
            return head;
        }

        ListNode slow = head;
        for (int i = 0; i < index; i++) {
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    private int getSize(ListNode node) {
        if (node.next == null) {
            return 1;
        }
        return getSize(node.next) + 1;
    }
}
