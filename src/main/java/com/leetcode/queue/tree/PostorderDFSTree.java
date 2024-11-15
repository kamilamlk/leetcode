package com.leetcode.queue.tree;

/**
 * Order: Left -> Right -> Root
 */
public class PostorderDFSTree {
    public void postorderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }

        postorderTraverse(node.left);
        postorderTraverse(node.right);
        System.out.print(node.val + " ");
    }
}
