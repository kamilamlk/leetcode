package com.leetcode.pointers;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode leftHead = new ListNode();
        ListNode rightHead = new ListNode();
        ListNode leftCurrent = leftHead;
        ListNode rightCurrent = rightHead;

        ListNode current = head;
        while (current != null) {
            if(current.val < x) {

                leftCurrent.next = current;
                leftCurrent = leftCurrent.next;
            } else {
                System.out.println("right current.val: " + current.val);
                rightCurrent.next = current;
                rightCurrent = rightCurrent.next;
            }
            current = current.next;
        }
        rightCurrent.next = null;
        leftCurrent.next = rightHead.next;

        return leftHead.next;
    }

    public static void main(String[] args) {
        PartitionList partition = new PartitionList();

        System.out.println(partition.partition(create(1,4,3,2,5,2), 3));
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
