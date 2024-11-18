package com.leetcode.queue.tree;

import java.util.ArrayList;
import java.util.List;

public class LevelOrderTraversal {
    private List<List<Integer>> levels = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return levels;
        }
        traverse(root, 0);
        return levels;
    }

    private void traverse(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(node.val);
        traverse(node.left, level + 1);
        traverse(node.right, level + 1);
    }

    public static void main(String[] args) {
        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(levelOrderTraversal.levelOrder(root));
    }
}
