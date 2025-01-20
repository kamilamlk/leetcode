package com.leetcode.queue.tree;


public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        if (left == -1) {
            return left;
        }
        int right = dfs(node.right);
        if (right == -1) {
            return right;
        }
        if (left - right < -1 || left - right > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(balancedBinaryTree.isBalanced(root));
    }
}
