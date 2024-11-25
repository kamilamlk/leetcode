package com.leetcode.queue.tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Time: O(n)
 * Space: O(h) for recursion
 */
public class LowestCommonAncestorFaster {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // if root is p or q return root and don't go further
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }


    public static void main(String[] args) {
        LowestCommonAncestorFaster lowestCommonAncestor = new LowestCommonAncestorFaster();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println(lowestCommonAncestor.lowestCommonAncestor(root, root.left, root.right).val);
    }
}
