package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    private enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int topBorder = 0;
        int rightBorder = matrix[0].length - 1;
        int bottomBorder = matrix.length - 1;
        int leftBorder = 0;
        Direction direction = (matrix[0].length == 1) ? Direction.DOWN : Direction.RIGHT;

        List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
        int row = 0;
        int col = 0;
        for (int i = 0; i < matrix.length * matrix[0].length; i++) {
            result.add(matrix[row][col]);
            switch (direction) {
                case RIGHT:
                    col++;
                    if (col == rightBorder) {
                        direction = Direction.DOWN;
                        topBorder++;
                    }
                    break;
                case DOWN:
                    row++;
                    if (row == bottomBorder) {
                        direction = Direction.LEFT;
                        rightBorder--;
                    }
                    break;
                case LEFT:
                    col--;
                    if (col == leftBorder) {
                        direction = Direction.UP;
                        bottomBorder--;
                    }
                    break;
                case UP:
                    row--;
                    if (row == topBorder) {
                        direction = Direction.RIGHT;
                        leftBorder++;
                    }
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(spiralMatrix.spiralOrder(matrix));

        matrix = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println(spiralMatrix.spiralOrder(matrix));

        matrix = new int[][]{
            {3}, {2}
        };
        System.out.println(spiralMatrix.spiralOrder(matrix));

    }
}
