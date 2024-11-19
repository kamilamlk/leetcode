package com.leetcode.queue.tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTreePreorder {
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(
            preorder, 0, preorder.length - 1,
            0, inorder.length - 1
        );
    }

    private TreeNode buildTree(
        int[] preorder, int preStart, int preEnd,
        int inStart, int inEnd
    ) {
        if (inStart > inEnd || preStart > preEnd || preStart < 0 || inStart < 0) {
            return null;
        }

        int inMid = inorderMap.get(preorder[preStart]);
        int preMid = preStart + inMid - inStart;

        TreeNode root = new TreeNode(preorder[preStart]);
        root.left = buildTree(
            preorder, preStart + 1, preMid,
            inStart, inMid - 1
        );
        root.right = buildTree(
            preorder, preMid + 1, preEnd,
            inMid + 1, inEnd
        );
        return root;
    }

    public static void main(String[] args) {
        test(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    private static void test(int[] preorder, int[] inorder) {
        ConstructBTreePreorder constructBTreePreorder = new ConstructBTreePreorder();
        TreeNode root = constructBTreePreorder.buildTree(preorder, inorder);
        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
        System.out.println(levelOrderTraversal.levelOrder(root));
    }
}
