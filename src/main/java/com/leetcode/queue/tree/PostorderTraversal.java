package com.leetcode.queue.tree;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal {
    /**
     * Order: left -> right -> root
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }
}
