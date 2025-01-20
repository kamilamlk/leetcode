package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class RemoveNthNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = getSize(head);
        if (size <= 1) {
            return null;
        }

        int indexSize = size - n;
        int i = 1;
        ListNode node = head;
        while(i < indexSize && node != null) {
            node = node.next;
            i++;
        }
        if (node == null || node.next == null) {
            return head;
        } else {
            node.next = node.next.next;
        }
        return head;
    }

    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static void main(String[] args) {
        RemoveNthNode removeNthNode = new RemoveNthNode();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(removeNthNode.removeNthFromEnd(head, 2));
        System.out.println(removeNthNode.removeNthFromEnd(new ListNode(1), 1));
        System.out.println(removeNthNode.removeNthFromEnd(new ListNode(1, new ListNode(2)), 1));
    }
}
