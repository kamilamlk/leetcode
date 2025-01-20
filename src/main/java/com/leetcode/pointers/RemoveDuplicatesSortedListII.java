package com.leetcode.pointers;

public class RemoveDuplicatesSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
       
    }

    public static void main(String[] args) {
        ListNode node = toListNode(1, 2, 2, 3, 4, 5, 5);
        RemoveDuplicatesSortedListII removeDuplicates = new RemoveDuplicatesSortedListII();
        ListNode result = removeDuplicates.deleteDuplicates(node);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
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
