package com.leetcode.arrays;

import javax.swing.tree.TreeNode;

public class ArrayToBinaryTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return balance(nums, 0, nums.length);
    }

    private TreeNode balance(int[] nums, int left, int right) {
        if (right <= left || left < 0 || right > nums.length) {
            return null;
        }
        int mid = (right + left) / 2;
        var node = new TreeNode(nums[mid]);
        node.left = balance(nums, left, mid);
        node.right = balance(nums, mid + 1, right);
        return node;
    }

    public static void main(String[] args) {
        ArrayToBinaryTree solution = new ArrayToBinaryTree();
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = solution.sortedArrayToBST(nums);
        // You can add code here to print or verify the structure of the tree

    }

}
