package com.leetcode.queue.tree;

/**
 * Time complexity O(N)
 * Space complexity O(1) if we don't consider the recursion stack otherwise O(h) where h is the height of the tree
 * Order: Root -> Left -> Right
 *  Visit the root.
 *  Traverse the left subtree, i.e., call Preorder(left->subtree)
 *  Traverse the right subtree, i.e., call Preorder(right->subtree)
 */
public class PreorderDFSTree {

    public void preorderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preorderTraverse(node.left);
        preorderTraverse(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        PreorderDFSTree preorderDFSTree = new PreorderDFSTree();
        System.out.print("Preorder: ");
        preorderDFSTree.preorderTraverse(root);

        InorderDFSTree inorderDFSTree = new InorderDFSTree();
        System.out.println("Inorder: ");
        inorderDFSTree.inorderTraverse(root);
    }
}
