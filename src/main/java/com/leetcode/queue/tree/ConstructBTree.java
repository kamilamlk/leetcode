package com.leetcode.queue.tree;

public class ConstructBTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {

    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

    }
}
