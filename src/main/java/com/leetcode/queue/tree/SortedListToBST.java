package com.leetcode.queue.tree;

import com.leetcode.pointers.ListNode;

public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode current = new TreeNode(slow.next.val);
        ListNode rightHalf = slow.next.next;
        slow.next = null;
        current.left = sortedListToBST(head);
        current.right = sortedListToBST(rightHalf);
        return current;
    }

    public static void main(String[] args) {
        SortedListToBST toBST = new SortedListToBST();
        var result = toBST.sortedListToBST(toListNode(-10,-3,0,5,9));
        System.out.println(result);
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
