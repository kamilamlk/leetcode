package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

public class RemoveZero {
    public ListNode removeZeroSumSublists(ListNode head) {
        int size = 0;
        ListNode current = head;
        while(current != null) {
            current = current.next;
            size++;
        }
        int[] array = new int[size];
        current = head; int i = 0;
        while(current != null) {
            array[i] = current.val;
            i++;
            current = current.next;
        }

        for (int j = 0; j < array.length; j++) {
            if (array[j] == -1001) {
                continue;
            }
            int sum = 0;
            for (int k = j; k < array.length; k++) {
                sum += array[k];
                if (sum == 0) {
                    for (int l = j; l <= k; l++) {
                        array[l] = -1001;
                    }
                    break;
                }
            }
        }
        ListNode newHead = new ListNode();
        current = newHead;
        for (int k : array) {
            if (k != -1001) {
                current.next = new ListNode(k);
                current = current.next;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        RemoveZero removeZero = new RemoveZero();
        System.out.println(removeZero.removeZeroSumSublists(ListNode.create(1, 2, -3, 3, 1)));
        System.out.println(removeZero.removeZeroSumSublists(ListNode.create(1, 2, 3, -3, 4)));
        System.out.println(removeZero.removeZeroSumSublists(ListNode.create(1, 2, 3, -3, -2)));
    }
}
