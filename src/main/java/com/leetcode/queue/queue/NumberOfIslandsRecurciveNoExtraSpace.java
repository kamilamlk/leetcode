package com.leetcode.queue.queue;

public class NumberOfIslandsRecurciveNoExtraSpace {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[row].length ||
            grid[row][col] == '0'
        ) {
            return;
        }

        if (grid[row][col] == '1') {
            grid[row][col] = '0';
            bfs(grid, row - 1, col); // top
            bfs(grid, row, col + 1); // right
            bfs(grid, row, col - 1); // left
            bfs(grid, row + 1, col); // bottom
        }
    }

    public static void main(String[] args) {
        NumberOfIslandsRecurciveNoExtraSpace numberOfIslands = new NumberOfIslandsRecurciveNoExtraSpace();
        char[][] grid = new char[][] {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println(numberOfIslands.numIslands(grid));
    }
}
