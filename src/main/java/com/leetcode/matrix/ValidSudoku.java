package com.leetcode.matrix;

public class ValidSudoku {
    /*
    not in row i, 0..9: for i, j check i,0...9
    not in column 0..9,j: for [i,j] check 0..9,j
    not in square: for [i,j] check for i / 3 * 3 ... i / 3 * 3 + 3
                                    for j / 3 * 3 ... j /3 * 3 + 3
     */
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(notValid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean notValid(char[][] board, int i, int j) {
        return notRow(board, i, j) || notColumn(board, i, j) || notSquare(board, i, j);
    }

    private boolean notColumn(char[][] board, int i, int j) {
        for(int k = 0; k < board.length; k++) {
            if(i != k && board[i][j] == board[k][j]) {
                return true;
            }
        }
        return false;
    }

    private boolean notRow(char[][] board, int i, int j) {
        for(int k = 0; k < board[i].length; k++) {
            if(j != k && board[i][j] == board[i][k]) {
                return true;
            }
        }
        return false;
    }

    private boolean notSquare(char[][] board, int i, int j) {
        int rowStart = i / 3 * 3;
        int colStart = j / 3 * 3;
        for(int k = rowStart; k < rowStart + 3; k++) {
            for(int m = colStart; m < colStart + 3; m++) {
                if(i != k && j != m && board[i][j] == board[k][m]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ValidSudoku validSudoku = new ValidSudoku();
        System.out.println(validSudoku.isValidSudoku(new char[][]{
            {'5','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}}
        ));
    }
}
