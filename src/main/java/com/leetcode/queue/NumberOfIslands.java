package com.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    Queue<Integer[]> queue = new LinkedList<>();
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
        queue.add(new Integer[] {rootRow, rootCol});
        visited[rootRow][rootCol] = true;
        while (!queue.isEmpty()) {
            Integer[] element = queue.poll();
            if (grid[element[0]][element[1]] == '1') {
                top(element[0] - 1, element[1], visited);
                right(element[0], element[1] + 1, visited);
                bottom(element[0] + 1, element[1], visited);
                left(element[0], element[1] - 1, visited);
            }
        }
    }

    private void top(int row, int col, boolean[][] visited) {
        if (row >= 0 && !visited[row][col]) {
            queue.add(new Integer[] {row, col});
            visited[row][col] = true;
        }
    }

    private void right(int row, int col, boolean[][] visited) {
        if (col < visited[row].length && !visited[row][col]) {
            queue.add(new Integer[] {row, col});
            visited[row][col] = true;
        }
    }

    private void bottom(int row, int col, boolean[][] visited) {
        if (row < visited.length && !visited[row][col]) {
            queue.add(new Integer[] {row, col});
            visited[row][col] = true;
        }
    }

    private void left(int row, int col, boolean[][] visited) {
        if (col >= 0 && !visited[row][col]) {
            queue.add(new Integer[] {row, col});
            visited[row][col] = true;
        }
    }

    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        char[][] grid = new char[][] {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println(numberOfIslands.numIslands(grid));
    }
}
