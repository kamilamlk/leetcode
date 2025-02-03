package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode current = result;
        int inMemory = 0;
        while(l1 != null && l2 != null) {
            int val = l1.val + l2.val + inMemory;
            if(val < 9) {
                current.next = new ListNode(val);
                inMemory = 0;
            } else if(val >= 10) {
                current.next = new ListNode(val % 10);
                inMemory = 1;
            }
            l1 = l1.next;
            l2 = l2.next;
            current = current.next;
        }
        while(l1 != null || l2 != null) {
            int val = (l1 != null ? l1.val : l2.val) + inMemory;
            if(val < 9) {
                current.next = new ListNode(val);
                inMemory = 0;
            } else if(val >= 10) {
                current.next = new ListNode(val % 10);
                inMemory = 1;
            }
            if(l1 != null) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
            current = current.next;
        }
        if(inMemory == 1) {
            current.next = new ListNode(1);
        }
        return result.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        //System.out.println(addTwoNumbers.addTwoNumbers(create(2, 4, 3), create(5, 6, 4)));
        System.out.println(addTwoNumbers.addTwoNumbers(create(4), create(5)));
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
