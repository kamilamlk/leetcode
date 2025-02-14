package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DeleteNodes {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet();
        for(int a : nums) {
            set.add(a);
        }

        ListNode current = head;
        ListNode prev = null;

        while(current != null) {
           if (set.contains(current.val)) {
                if (prev != null) {
                    prev.next = current.next;
                    current = current.next;
                } else {
                    head = current.next;
                    current = current.next;
                }
           } else {
                prev = current;
                current = current.next;
           }
        }
        return head;
    }

    public static void main(String[] args) {
        DeleteNodes deleteNodes = new DeleteNodes();
        System.out.println(deleteNodes.modifiedList(new int[]{3, 2, 1}, ListNode.create(3, 2, 1)));
        System.out.println(deleteNodes.modifiedList(new int[]{3, 2, 1}, ListNode.create(3, 2, 1, 4)));
        System.out.println(deleteNodes.modifiedList(new int[]{3, 2, 1}, ListNode.create(3, 2, 1, 4, 5)));
    }
}
