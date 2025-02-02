package com.leetcode.pointers;

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        ListNode current = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            current = current.next;
            fast = fast.next.next;

            if (current == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode head = create(3, 2, 0, -4);
        head.next.next.next.next = head.next;
        LinkedListCycle linkedListCycle = new LinkedListCycle();
        System.out.println(linkedListCycle.hasCycle(head));
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
