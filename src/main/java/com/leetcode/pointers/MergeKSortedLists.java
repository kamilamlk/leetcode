package com.leetcode.pointers;

import java.util.Arrays;
import java.util.Comparator;

public class MergeKSortedLists {
    Comparator<ListNode> comparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return Integer.compare(o1.val, o2.val);
        }
    };

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        Arrays.sort(lists, comparator);
        head.next = lists[0];
        ListNode current = head;
        while (current != null) {
            for (int i = 0; i < lists.length; i++) {
                current.next = lists[i];
                lists[i] = lists[i].next;
            }
        }

        return head.next;
    }
}
