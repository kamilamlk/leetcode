package com.leetcode.queue.tree;

/**
 * Time complexity O(N)
 * Space complexity O(1) if we don't consider the recursion stack otherwise O(h) where h is the height of the tree
 *
 * Idea: left -> root -> right
 */
public class InorderDFSTree {
    void inorderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        System.out.print(node.val + " ");
        inorderTraverse(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        InorderDFSTree inorderDFSTree = new InorderDFSTree();
        inorderDFSTree.inorderTraverse(root);
    }
}
