package com.leetcode.queue.tree;

public class Codec {
    private static String NULL = "null";
    private static String DELIMITER = ",";
    private int deserializeIndex = 0;

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(sb, root);
        return sb.toString();
    }

    private void serialize(StringBuilder sb, TreeNode node) {
        if (node == null) {
            sb.append(NULL).append(DELIMITER);
            return;
        }
        sb.append(node.val).append(DELIMITER);
        serialize(sb, node.left);
        serialize(sb, node.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(DELIMITER);
        deserializeIndex = 0;
        return deserialize(nodes);
    }

    private TreeNode deserialize(String[] nodes) {
        if (nodes[deserializeIndex].equals(NULL)) {
            deserializeIndex++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(nodes[deserializeIndex]));
        deserializeIndex++;
        node.left = deserialize(nodes);
        node.right = deserialize(nodes);
        return node;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = codec.serialize(root);
        System.out.println(serialized);

        TreeNode deserialized = codec.deserialize(serialized);

        System.out.println(deserialized);
    }
}
