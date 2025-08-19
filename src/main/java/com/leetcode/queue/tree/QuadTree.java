package com.leetcode.queue.tree;

public class QuadTree {

    // Definition for a QuadTree node.
    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid.length);
    }

    private Node construct(int[][] grid, int i, int j, int length) {
        if (i < 0 || i >= grid.length) {
            return null;
        }
        if (j < 0 || j >= grid[0].length) {
            return null;
        }

        if (length == 0) {
            return new Node(grid[i][j] == 1, true);
        }

        var topLeft = construct(grid, i, j, length / 2); // Top-left quadrant : 0, 0
        var topRight = construct(grid, i, j + length / 2, length / 2); // Top-right quadrant : 0, 1
        var bottomLeft = construct(grid, i + length / 2, j, length / 2);
        var bottomRight = construct(grid, i + length / 2, j + length / 2, length / 2);

        if (areEqualLeaves(topLeft, topRight, bottomLeft, bottomRight)) {
            return new Node(topLeft.val, true);
        } else {
            var node = new Node();
            node.isLeaf = false;
            node.topLeft = topLeft;
            node.topRight = topRight;
            node.bottomLeft = bottomLeft;
            node.bottomRight = bottomRight;
            return node;
        }
    }

    private boolean areEqualLeaves(Node... nodes) {
        var firstVal = nodes[0].val;
        for (Node node : nodes) {
            if (node == null || !node.isLeaf) {
                return false;
            }
            if (node.val != firstVal) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        QuadTree quadTree = new QuadTree();
        int[][] grid = {
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {1, 1, 0, 0},
            {0, 0, 1, 1}
        };
        Node root = quadTree.construct(grid);
        // You can add code here to print or verify the constructed QuadTree
        System.out.println("QuadTree constructed successfully.");
    }
}
