package com.leetcode.frequent.elemts;

public class Sudoku {
    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *<p>
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * Note:
     *<p>
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] squads = new int[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            int[] horizontalLines = new int[board.length];
            int[] verticalLines = new int[board.length];

            for (int j = 0; j < board[i].length; j++) {
                if (isNotValid(toDigit(board[i][j]) - 1, horizontalLines)) {
                    return false;
                }
                if (isNotValid(toDigit(board[j][i]) - 1, verticalLines)) {
                    return false;
                }
                int squad = getSquad(i, j);
                if (isNotValid(toDigit(board[i][j]) - 1, squads[squad])) {
                    return false;
                }
            }
        }

        return true;
    }

    private int getSquad(int i, int j) {
        if (i < 3) {
            if (j < 3) {
                return 0;
            } else if (j < 6) {
                return 1;
            } else {
                return 2;
            }
        } else if (i < 6) {
            if (j < 3) {
                return 3;
            } else if (j < 6) {
                return 4;
            } else {
                return 5;
            }
        } else {
            if (j < 3) {
                return 6;
            } else if (j < 6) {
                return 7;
            } else {
                return 8;
            }
        }
    }

    private boolean isNotValid(int value, int[] lines) {
        if (value >= 0) {
            if (lines[value] > 0) {
                return true;
            } else {
                lines[value] = lines[value] + 1;
                return false;
            }
        } else {
            return false;
        }
    }

    private int toDigit(char value) {
        if (Character.isDigit(value)) {
            return Character.getNumericValue(value);
        } else {
            return 0;
        }
    }
}
