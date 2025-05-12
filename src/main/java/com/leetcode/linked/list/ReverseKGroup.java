package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class ReverseKGroup {
    ListNode dummy;
    ListNode start;
    int i = 0;

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        if (head == null || head.next == null) {
            return head;
        }

        init();

        int n = 0;
        ListNode current = head;
        while(current != null) {
            n++;
            current = current.next;
        }
        dummy.next = head;

        ListNode tail = dummy;
        current = dummy;
        while(i + k <= n) {
            tail = reverse(tail.next, tail.next, i + k);
            current.next = start;
            current = tail;
        }

        return dummy.next;
    }

    private void init() {
        dummy = new ListNode(0);
        start = null;
        i = 0;
    }


    private ListNode reverse(ListNode head, ListNode node, int right) {
        i++;
        if(i == right) {
            start = node;
            head.next = node.next;
            return node;
        }
        var current = reverse(head, node.next, right);
        current.next = node;
        return node;
    }


    public static void main(String[] args) {
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        ListNode head = create(1, 2, 3, 4, 5, 6, 7);
        System.out.println(reverseKGroup.reverseKGroup(head, 3));
        head = create(1, 2, 3, 4, 5);
        System.out.println(reverseKGroup.reverseKGroup(head, 2));
        head = create(1, 2, 3, 4, 5);
        System.out.println(reverseKGroup.reverseKGroup(head, 3));
    }


    private static ListNode create(int... vars) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int var : vars) {
            node.next = new ListNode(var);
            node = node.next;
        }
        return head.next;
    }
}
