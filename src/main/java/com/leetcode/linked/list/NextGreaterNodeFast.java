package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterNodeFast {
    public int[] nextLargerNodes(ListNode head) {
        int size = 0;
        ListNode current = head;
        while(current != null) {
            size++;
            current = current.next;
        }

        Stack<Integer> stack = new Stack<>();
        int[] array = new int[size];
        return array;
    }

    public static void main(String[] args) {
        NextGreaterNodeFast nextGreaterNode = new NextGreaterNodeFast();
        System.out.println(Arrays.toString(nextGreaterNode.nextLargerNodes(create(2, 7, 4, 3, 5))));
        //System.out.println(Arrays.toString(nextGreaterNode.nextLargerNodes(create(2, 1, 5))));
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
