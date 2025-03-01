package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class Palindrome {
    boolean isPalindrome = true;
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        isPalindrome(head, size, 0);
        return isPalindrome;
    }
    // 0 1 2 3
    // 1 2 2 1
    // [1, 4, 0] -> [2, 4, 1]
    //
    private ListNode isPalindrome(ListNode node, int size, int i) {
        if(size % 2 == 1 && size / 2 == i) {
            return node.next;
        } else if(size % 2 == 0 && size / 2 == i) {
            return node;
        }
        ListNode next = isPalindrome(node.next, size, i + 1);
        System.out.println(node.val + " " + next.val + " " + isPalindrome);
        if(!isPalindrome || node.val != next.val) {
            isPalindrome = false;
        }
        return next.next;
    }


    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.isPalindrome(create(1,2,2,2,1)));
        System.out.println(p.isPalindrome(create(1,2,2,1)));
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
