package com.leetcode.queue.tree;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return hasPathSum(root, 0, targetSum);
    }

    private boolean hasPathSum(TreeNode node, int currentSum, int targetSum) {
        if (node == null) {
            return false;
        }
        if (isLeaf(node)) {
            return (currentSum + node.val) == targetSum;
        } else {
            return hasPathSum(node.left, currentSum + node.val, targetSum)
                || hasPathSum(node.right, currentSum + node.val, targetSum);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
    public static void main(String[] args) {
        PathSum pathSum = new PathSum();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        System.out.println(pathSum.hasPathSum(root, 22));
    }
}
