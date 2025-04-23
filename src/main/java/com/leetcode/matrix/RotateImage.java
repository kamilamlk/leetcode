package com.leetcode.matrix;

public class RotateImage {
    /**
     Intuition
        left [i,j] -> [j, n - i - 1]
        down [i,j] -> [n - j - 1, n - i - 1]
        right [i,j] -> [n - i - 1, j]
        up [i,j] -> [j, i]

     boundaries
        i -> [0, n/2)
        j -> [i, n - i - 1]
     Approach

     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++) {
            for(int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];                 // Move P4 to P1
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j]; // Move P3 to P4
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i]; // Move P2 to P3
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage rotateImage = new RotateImage();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotateImage.rotate(matrix);
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
