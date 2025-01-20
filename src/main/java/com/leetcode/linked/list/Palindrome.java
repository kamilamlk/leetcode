package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class Palindrome {
    public boolean isPalindrome(ListNode head) {
        int last = getLastIndex(head);
        var node = isPalindrome(head, 0, last);
        return node != null;
    }

    private ListNode isPalindrome(ListNode node, int i, int size) {
        if (i >= size / 2) {
            return node.next;
        }

        var next = isPalindrome(node.next, i + 1, size);
        if (next != null && node.val == next.val) {
            return next.next;
        } else {
            return null;
        }
    }


    private int getLastIndex(ListNode node) {
        if (node == null) {
            return 0;
        } else {
            return getLastIndex(node.next) + 1;
        }
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.isPalindrome(create(1, 2, 2, 1)));
    }

    private static ListNode create(int... vars) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int var : vars) {
            node.next = new ListNode(var);
        }
        return head.next;
    }
}
