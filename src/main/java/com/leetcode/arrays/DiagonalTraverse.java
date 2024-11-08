package com.leetcode.arrays;

import java.util.Arrays;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int[] result = new int[mat.length * mat[0].length];
        int row = 0;
        int col = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = mat[row][col];
            if ((row + col) % 2 == 0) {
                if (col == mat[0].length - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else {
                if (row == mat.length - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DiagonalTraverse diagonalTraverse = new DiagonalTraverse();
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] result = diagonalTraverse.findDiagonalOrder(mat);
        System.out.println(Arrays.toString(result));
    }
}
