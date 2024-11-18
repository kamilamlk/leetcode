package com.leetcode.queue.tree;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal {
    /**
     * Order root -> left -> right
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        var list = new ArrayList<Integer>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
