package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            n++;
        }
        int step = n / k;
        ListNode dummy = new ListNode(0);
        for (int i = 0; i < n; i+= step) {

        }
    }

    ListNode tail;
    ListNode start;

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int i = 0;
        ListNode current = dummy;
        while (i < left - 1) {
            current = current.next;
            i++;
        }
        var next = reverse(current.next, right, i + 1);
        next.next = tail;
        current.next = start;
        return dummy.next;
    }

    private ListNode reverse(ListNode node, int right, int i) {
        if(i == right) {
            tail = node.next;
            start = node;
            return node;
        }
        var current = reverse(node.next, right, i+1);
        current.next = node;
        return node;
    }

}
