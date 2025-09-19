package com.leetcode.design;

import com.leetcode.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class CBTInserter {
    Deque<TreeNode> currentLevel;
    Deque<TreeNode> nextLevel;
    TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
        this.currentLevel = new ArrayDeque<>();
        this.nextLevel = new ArrayDeque<>();
        fillLevel();
    }

    private void fillLevel() {
        currentLevel.add(root);
        while(!currentLevel.isEmpty()) {
            TreeNode current = currentLevel.peek();
            if (current.left == null) {
                break;
            }
            nextLevel.add(current.left);
            if (current.right == null) {
                break;
            }
            nextLevel.add(current.right);
            currentLevel.poll();
            if(currentLevel.isEmpty()) {
                Deque<TreeNode> temp = currentLevel;
                this.currentLevel = nextLevel;
                this.nextLevel = temp;
            }

        }
    }

    public int insert(int val) {
        TreeNode node = new TreeNode(val);

        if(currentLevel.isEmpty()) {
            Deque<TreeNode> temp = currentLevel;
            this.currentLevel = nextLevel;
            this.nextLevel = temp;
        }

        TreeNode parent = currentLevel.peek();
        if(parent.left == null) {
            parent.left = node;
        } else {
            parent.right = node;
            currentLevel.poll();
        }
        nextLevel.add(node);
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }

    public static void main(String[] args) {
        /**
         * ["CBTInserter","insert","insert","get_root"]
         * [[[1,2]],[3],[4],[]]
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        CBTInserter cbtInserter = new CBTInserter(root);
        System.out.println(cbtInserter.insert(3)); // return 1
        System.out.println(cbtInserter.insert(4)); // return 2
        System.out.println(cbtInserter.get_root().val); // return [1,2,3,4]
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */