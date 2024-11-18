package com.leetcode.queue.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Order: visit all nodes in the same level completely before moving to the next level
 * Mainly used in BFS to search or process level by level
 */
public class LevelOrderBFSTree {
    public void levelOrderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}
