package com.leetcode.matrix;

public class NQueens {
    int count = 0;
    int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        backtrack(board, 0, n);
        return count;
    }

    /**
     *  board[i][j] - one per line,
     *                one per column,
     *                one per diagonal left to right,
     *                one per diagonal right to left
     */
    boolean hasConflict(boolean[][] board, int i, int j) {
        for (int k = 0; k < j; k++) {
            if (board[i][k]) return true;
        }
        for (boolean[] booleans : board) {
            if (booleans[j]) return true;
        }
        for (int k = i, l = j; k >= 0 && l >= 0; k--, l--) {
            if (board[k][l]) return true;
        }
        for (int k = i, l = j; k >= 0 && l < board.length; k--, l++) {
            if (board[k][l]) return true;
        }
        return false;
    }

    void backtrack(boolean[][] board, int i, int n) {
        if (n == 0) {
            count++;
        }
        if (i >= board.length) return;

        for(int j = 0; j < board[i].length; j++) {
            if (!hasConflict(board, i, j)) {
                board[i][j] = true;
                backtrack(board, i + 1, n - 1);
                board[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.totalNQueens(4));
    }
}
