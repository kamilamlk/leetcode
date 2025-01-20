package com.leetcode.pointers;

public class RemoveDuplicateEasy {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }

    public static void main(String[] args) {
        RemoveDuplicateEasy removeDuplicate = new RemoveDuplicateEasy();
        ListNode head = create(1, 1, 2, 3, 3);
        //System.out.println(removeDuplicate.deleteDuplicates(head));
        //System.out.println(removeDuplicate.deleteDuplicates(create(1, 1, 1, 2, 3)));
        System.out.println(removeDuplicate.deleteDuplicates(create(1, 2, 3, 3, 4, 4, 5)));
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
