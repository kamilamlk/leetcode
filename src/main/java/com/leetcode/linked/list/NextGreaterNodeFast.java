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
        // swap list
        ListNode prev = null;
        current = head;
        while (current != null) {
            ListNode next = current.next; // 1
            current.next = prev; // null
            prev = current; // 2
            current = next; // 1
        }
        Stack<Integer> stack = new Stack<>();
        int[] array = new int[size];
        current = prev;
        while(current != null) {
            while (!stack.isEmpty() && stack.peek() <= current.val) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                array[--size] = 0;
            } else {
                array[--size] = stack.peek();
            }
            stack.push(current.val);
            current = current.next;
        }
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
