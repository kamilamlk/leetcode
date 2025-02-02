package com.leetcode.queue.tree;

public class FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        flatten(root, null);
    }

    private TreeNode flatten(TreeNode node, TreeNode prev) {
        if (node == null) {
            return prev;
        }

        prev = flatten(node.right, prev);
        prev = flatten(node.left, prev);
        node.right = prev;
        node.left = null;

        return node; // as prev
    }

    public static void main(String[] args) {
        FlattenBinaryTreetoLinkedList binaryTreetoLinkedList = new FlattenBinaryTreetoLinkedList();
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2,
                new TreeNode(3),
                new TreeNode(4)
            ), new TreeNode(5, null, new TreeNode(6)));
        binaryTreetoLinkedList.flatten(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
}
