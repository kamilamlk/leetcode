package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class SortList {

    private ListNode split(ListNode head) {
        // slow fast
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;
        return slow;
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode second = split(head);
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(second);
        return merge(left, right);
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    public static void main(String[] args) {
        SortList sortList = new SortList();
        System.out.println(sortList.sortList(ListNode.create(4, 2, 1, 3)));
        System.out.println(sortList.sortList(ListNode.create(-1, 5, 3, 4, 0)));
        System.out.println(sortList.sortList(ListNode.create()));
    }
}
