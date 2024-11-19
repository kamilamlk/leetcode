package com.leetcode.queue.tree;

public class ConstructBTreePostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(
            inorder, 0, inorder.length - 1,
            postorder, 0, postorder.length - 1
        );
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd || postStart < 0 || inStart < 0) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int inMid = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                inMid = i;
                break;
            }
        }

        int postMid = postStart + inMid - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(
            inorder, inStart, inMid - 1,
            postorder, postStart, postMid - 1
        );
        root.right = buildTree(
            inorder,inMid + 1, inEnd,
            postorder, postMid, postEnd - 1
        );

        return root;
    }

    public static void main(String[] args) {
       // test(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        test(new int[]{2, 1}, new int[]{2, 1});
    }

    private static void test(int[] inorder, int[] postorder) {
        ConstructBTreePostorder constructBTreePostorder = new ConstructBTreePostorder();
        TreeNode root = constructBTreePostorder.buildTree(inorder, postorder);
        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
        System.out.println(levelOrderTraversal.levelOrder(root));
    }
}
