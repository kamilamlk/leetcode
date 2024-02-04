package com.leetcode.pointers;

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = getSize(head);
        int index = size - n + 1;
        int i = 0;
        ListNode prev = head;

        while (i < index) {
            var cur = prev.next;
            prev = cur;
            i++;
        }
        if (i == index) {
            ListNode temp = prev.next;
            prev.next = temp.next;
        }
        return head;
    }

    private int getSize(ListNode node) {
        if (node.next == null) {
            return 1;
        }
        return getSize(node.next) + 1;
    }
}
