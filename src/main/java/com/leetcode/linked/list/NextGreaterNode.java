package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;
import java.util.Arrays;

public class NextGreaterNode {
    public int[] nextLargerNodes(ListNode head) {
        int size = 0;
        ListNode current = head;
        while(current != null) {
            size++;
            current = current.next;
        }
        int[] array = new int[size];
        current = head;
        ListNode next = current.next;
        for(int i = 0; i < size; i++) {
            while(next != null && current.val >= next.val) {
                next = next.next;
            }
            if(next != null) {
                array[i] = next.val;
            }
            current = current.next;
            if(current != null) {
                next = current.next;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        NextGreaterNode nextGreaterNode = new NextGreaterNode();
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
