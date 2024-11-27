package com.leetcode.queue.queue;

public class NumberOfIslandsRecurcive {
    boolean[][] visited;
    public int numIslands(char[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, boolean[][] visited, int rootRow, int rootCol) {
        if (rootRow < 0 || rootRow >= grid.length ||
            rootCol < 0 || rootCol >= grid[rootRow].length ||
            visited[rootRow][rootCol]
        ) {
            return;
        }
        visited[rootRow][rootCol] = true;

        if (grid[rootRow][rootCol] == '1') {

            bfs(grid, visited, rootRow - 1, rootCol); // top
            bfs(grid, visited, rootRow, rootCol + 1); // right
            bfs(grid, visited, rootRow, rootCol - 1); // left
            bfs(grid, visited, rootRow + 1, rootCol); // bottom
        }
    }

    public static void main(String[] args) {
        NumberOfIslandsRecurcive numberOfIslands = new NumberOfIslandsRecurcive();
        char[][] grid = new char[][] {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println(numberOfIslands.numIslands(grid));
    }
}
