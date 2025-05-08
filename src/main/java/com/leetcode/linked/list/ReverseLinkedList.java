package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class ReverseLinkedList {
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

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode head = create(1, 2, 3, 4, 5);
        System.out.println(reverseLinkedList.reverseBetween(head, 2, 4));
        head = create(1, 2, 3, 4, 5, 6);
        System.out.println(reverseLinkedList.reverseBetween(head, 2, 6));
        head = create(3, 5);
        System.out.println(reverseLinkedList.reverseBetween(head, 1,2));
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
