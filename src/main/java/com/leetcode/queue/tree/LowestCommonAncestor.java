package com.leetcode.queue.tree;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (isDescendant(root, p) && isDescendant(root, q)) {
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) {
                return root;
            } else if (left != null) {
                return left;
            } else {
                return right;
            }
        }
        return null;
    }

    private boolean isDescendant(TreeNode node, TreeNode p) {
        if (node == null) {
            return false;
        }
        if (node == p) {
            return true;
        }
        return isDescendant(node.left, p) || isDescendant(node.right, p);
    }

    public static void main(String[] args) {
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
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
