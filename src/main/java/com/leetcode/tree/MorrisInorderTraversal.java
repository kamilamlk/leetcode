package com.leetcode.tree;

public class MorrisInorderTraversal {
    public void morrisInorder(TreeNode root) {
        TreeNode current = root;

        while (current != null) {
            if(current.left == null) {
                System.out.print(current.val + " "); // process the current node
                current = current.right; // move to the right child
            } else {
                TreeNode pre = current.left; // get the left child
                while (pre.right != null && pre.right != current) {
                    pre = pre.right; // go to the right most node. in bst it will be the largest node
                }
                if (pre.right == null) {
                    pre.right = current; // make current as the right child of the right most node
                    current = current.left; // move to the left child of current
                } else {
                    System.out.print(current.val + " "); // process the current node
                    current = current.right; // move to the right child of current
                }
            }
        }
    }

    public static void main(String[] args) {
        /*
                1
               / \
              2   3
             / \
            4   5
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        MorrisInorderTraversal morris = new MorrisInorderTraversal();
        morris.morrisInorder(root); // Output: 4 2 5 1 3
    }
}
