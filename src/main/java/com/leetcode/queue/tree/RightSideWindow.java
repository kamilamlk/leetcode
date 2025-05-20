package com.leetcode.queue.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class RightSideWindow {
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        stack.offer(root);
        while(!stack.isEmpty()) {
            int size = stack.size();
            TreeNode current;
            for (int i = 0; i < size; i++) {
                current = stack.pollFirst();
                if (i == 0) {
                    result.add(current.val);
                }
                if(current.right != null) {
                    stack.offerLast(current.right);
                }
                if(current.left != null) {
                    stack.offerLast(current.left);
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        RightSideWindow rsw = new RightSideWindow();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        System.out.println(rsw.rightSideView(root)); // Output: [1, 3, 4]
    }
}

