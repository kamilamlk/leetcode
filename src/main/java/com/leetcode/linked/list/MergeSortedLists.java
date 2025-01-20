package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class MergeSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head;
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            head = list1;
            head.next = mergeTwoLists(list1.next, list2);
        } else {
            head = list2;
            head.next = mergeTwoLists(list1, list2.next);
        }
        return head;
    }

    public static void main(String[] args) {
        MergeSortedLists mergeSortedLists = new MergeSortedLists();
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(mergeSortedLists.mergeTwoLists(list1, list2));
    }
}
