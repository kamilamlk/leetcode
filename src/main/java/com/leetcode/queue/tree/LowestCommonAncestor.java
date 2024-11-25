package com.leetcode.queue.tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Time: O(n)
 * Space: O(h) for recursion
 */
public class LowestCommonAncestor {
    private TreeNode lca = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        isDescendant(root, p, q);
        return lca;
    }

    private boolean isDescendant(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }

        var left = isDescendant(node.left, p, q);
        var right = isDescendant(node.right, p, q);

        System.out.println("node: " + node.val + ", left: " + left + " right: " + right);
        if (node.val == p.val || node.val == q.val) {
            if (left || right) {
                lca = node;
            }
            return true;
        }
        if (left && right) {
            lca = node;
        }
        return left || right;
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
