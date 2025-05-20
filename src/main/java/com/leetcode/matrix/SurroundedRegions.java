package com.leetcode.matrix;

public class SurroundedRegions {
    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        int rightIdx = board[0].length - 1;
        int bottomIdx = board.length - 1;
        for(int i = 0; i < board.length; i++) {
            check(board, visited, i, 0);
            check(board, visited, i, rightIdx);
        }
        for(int i = 0; i < board[0].length; i++) {
            check(board, visited, 0, i);
            check(board, visited, bottomIdx, i);
        }

        for(int i = 1; i < board.length - 1; i++) {
            for(int j = 1; j < board[i].length - 1; j++) {
                if(visited[i][j]) continue;
                if(board[i][j] == 'O') mark(board, visited, i, j, true);
                else visited[i][j] = true;
            }
        }
        System.out.println("");
    }

    private void check(char[][] board, boolean[][] visited, int i, int j) {
        if(i < 0 || i >= board.length) return;
        if(j < 0 || j >= board[i].length) return;
        if(board[i][j] == 'O') {
            mark(board, visited, i, 0, false);
        } else {
            visited[i][j] = true;
        }
    }

    private void mark(char[][] board, boolean[][] visited, int i, int j, boolean replace) {
        if(i < 0 || i >= board.length) return;
        if(j < 0 || j >= board[i].length) return;
        if(visited[i][j]) return;

        visited[i][j] = true;

        if(board[i][j] == 'X') return;
        if(replace) board[i][j] = 'X';

        mark(board, visited, i, j + 1, replace);
        mark(board, visited, i, j - 1, replace);
        mark(board, visited, i + 1, j, replace);
        mark(board, visited, i - 1, j, replace);
    }

    public static void main(String[] args) {
        char[][] board = {
            {'X','O','X','O','X','O'},
            {'O','X','O','X','O','X'},
            {'X','O','X','O','X','O'},
            {'O','X','O','X','O','X'}
        };
        SurroundedRegions sr = new SurroundedRegions();
        sr.solve(board);

        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + ' ');
            }
            System.out.println();
        }
    }
}
