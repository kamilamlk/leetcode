package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class PalindromeFast {
    boolean isPalindrome = true;
    public boolean isPalindrome(ListNode head) {
        // 1 2 2 1
        // get the middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // swap the second half
        ListNode prev = null;
        ListNode current = slow;
        while (current != null) {
            ListNode next = current.next; // 1
            current.next = prev; // null
            prev = current; // 2
            current = next; // 1
        }

        while (prev != null) {
            if (head.val != prev.val) {
                return false;
            } else {
                prev = prev.next;
                head = head.next;
            }
        }
        return true;
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
        PalindromeFast p = new PalindromeFast();
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
