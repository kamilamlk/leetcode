package com.leetcode.queue.tree;

public class TraverseTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        PreorderDFSTree preorderDFSTree = new PreorderDFSTree();
        System.out.print("Preorder:    ");
        preorderDFSTree.preorderTraverse(root);

        InorderDFSTree inorderDFSTree = new InorderDFSTree();
        System.out.print("\nInorder:     ");
        inorderDFSTree.inorderTraverse(root);

        PostorderDFSTree postorderDFSTree = new PostorderDFSTree();
        System.out.print("\nPostorder:   ");
        postorderDFSTree.postorderTraverse(root);

        LevelOrderBFSTree levelOrderBFSTree = new LevelOrderBFSTree();
        System.out.print("\nLevel order: ");
        levelOrderBFSTree.levelOrderTraverse(root);
    }
}
