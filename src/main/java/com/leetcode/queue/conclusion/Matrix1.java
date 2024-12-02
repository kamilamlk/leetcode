package com.leetcode.queue.conclusion;

public class Matrix1 {
    public int[][] updateMatrix(int[][] mat) {
        int[][] distance = new int[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {

                bfs(distance, mat, i, j);
            }
        }
        return distance;
    }

    private int bfs(int[][] distance, int[][] mat, int row, int col) {
        if (row < 0 || row >= mat.length ||
            col < 0 || col >= mat[row].length || distance[row][col] == 0
        ) {
            return Integer.MAX_VALUE;
        }
        if (mat[row][col] == 0) {
            return 1;
        }

        int top = bfs(distance, mat, row - 1, col);
        int left = bfs(distance, mat, row, col - 1);
        int right = bfs(distance, mat, row, col + 1);
        int bottom = bfs(distance, mat, row + 1, col);
        return Math.min(top, Math.min(left, Math.min(right, bottom)));
    }

    public static void main(String[] args) {
        int[][] mat = new int[][] {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        //test(mat);
        int[][] mat1 = new int[][] {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };
        test(mat1);
    }

    private static void test(int[][] mat) {
        Matrix1 matrix1 = new Matrix1();
        int[][] result = matrix1.updateMatrix(mat);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
