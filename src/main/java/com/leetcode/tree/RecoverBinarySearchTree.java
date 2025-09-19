package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null;
        TreeNode prev = null, current = root;

        while (current != null) {
            if (current.left == null) {
                // --- Visit step ---
                if (prev != null && prev.val > current.val) {
                    if (first == null) first = prev;
                    second = current;
                }
                prev = current;
                current = current.right;
            } else {
                TreeNode pre = current.left;
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = current;
                    current = current.left;
                } else {
                    pre.right = null; // restore the tree

                    // --- Visit step (important, was missing in your code) ---
                    if (prev != null && prev.val > current.val) {
                        if (first == null) first = prev;
                        second = current;
                    }
                    prev = current;

                    current = current.right;
                }
            }
        }

        // Swap the values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public static void main(String[] args) {
        /*
                3
               / \
              1   4
                 /
                2
        */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        RecoverBinarySearchTree recoverBST = new RecoverBinarySearchTree();
        recoverBST.recoverTree(root);
        System.out.println(root.val); // Output: 2
        // The tree should now be a valid BST: [2,1,4,null,null,3]
    }
}
