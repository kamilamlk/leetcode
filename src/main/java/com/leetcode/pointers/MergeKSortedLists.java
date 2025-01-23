package com.leetcode.pointers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    Comparator<ListNode> comparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return Integer.compare(o1.val, o2.val) ;
        }
    };

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(Integer.MIN_VALUE);

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(comparator);
        ListNode current = head;
        for (int i = 0; i < lists.length; i++) {
            while (lists[i] != null) {
                priorityQueue.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode temp = priorityQueue.poll();
            if (current != null) {
                temp.next = null;
                current.next = temp;
                current = current.next;
            } else {
                current = temp;
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        System.out.println(
                mergeKSortedLists.mergeKLists(
                        new ListNode[] {
                                toListNode(-2,-1,-1,-1),
                                null
                        }
                )
        );
    }

    private static ListNode toListNode(int... vars) {
        ListNode head = new ListNode();
        ListNode current = head;
        for (int var : vars) {
            current.next = new ListNode(var);
            current = current.next;
        }
        return head.next;
    }
}
