package com.leetcode.pointers;

import org.junit.jupiter.api.Test;

public class RemoveNthFromEndTest {
    RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();

    @Test
    void test() {
        ListNode head = toListNode(new int[] {1,2,3,4,5});

        System.out.println(removeNthFromEnd.removeNthFromEnd(head, 2));

    }

    private ListNode toListNode(int[] array) {
        ListNode next = new ListNode(array[0]);
        ListNode fakeHead = new ListNode(1, next);
        for (int i = 1; i < array.length; i++) {
            next.next = new ListNode(array[i]);
            next = next.next;
        }
        return fakeHead.next;
    }
}
