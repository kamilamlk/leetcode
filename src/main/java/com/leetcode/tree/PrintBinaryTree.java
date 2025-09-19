package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTree {
    String[][] matrix;
    int rows;
    int cols;

    public List<List<String>> printTree(TreeNode root) {
        rows = getHeight(root);
        cols = pow(2, rows + 1) - 1;
        matrix = new String[rows][cols];
        place(root, 0, (cols - 1)/2);
        List<List<String>> result = new ArrayList<>();
        for(String[] row : matrix) {
            List<String> str = new ArrayList<>();
            for(String val : row) {
                if(val == null) {
                    str.add("");
                } else str.add(val);
            }
            result.add(str);
        }
        return result;
    }

    private int getHeight(TreeNode node) {
        if(node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private void place(TreeNode node, int i, int j) {
        if(node == null) return;
        matrix[i][j] = String.valueOf(node.val);
        int pow = pow(2, rows - j - 1);
        place(node.left, i + 1, j - pow);
        place(node.right, i + 1, j + pow);
    }

    private int pow(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        /*
                1
               / \
              2   3
               \
                4
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        PrintBinaryTree printer = new PrintBinaryTree();
        List<List<String>> result = printer.printTree(root);
        for (List<String> row : result) {
            System.out.println(row);
        }
    }
}
