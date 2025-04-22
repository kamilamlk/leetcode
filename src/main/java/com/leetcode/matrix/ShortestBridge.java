package com.leetcode.matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ShortestBridge {
    /**
     Intuition
        Use DFS for traversing the first island (BFS can also be used).
        Use BFS to find the shortest path to the second island.
     Approach
        Traverse the first island using DFS.
        While performing DFS, add all the water boundaries (i, j) where grid[i][j] = 0 of the first island to the queue.
        Utilize a multi-source BFS to determine the shortest path to the second island.
     */
    public int shortestBridge(int[][] grid) {
        Queue<Integer> queue = new LinkedList<>();
        boolean found = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        return bfs(grid, queue);
    }

    private int bfs(int[][] grid, Queue<Integer> queue) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int min = Integer.MAX_VALUE;

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                int x = index / grid[0].length;
                int y = index % grid[0].length;

                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];

                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                        if (grid[newX][newY] == 1) {
                            return level + 1;
                        } else if (grid[newX][newY] == 0) {
                            queue.offer(newX * grid[0].length + newY);
                            grid[newX][newY] = 2; // Mark as visited
                        }
                    }
                }
            }
            level++;
        }

        return min;
    }

    private void dfs(int[][] grid, int i, int j, Queue<Integer> queue) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 2) {
            return;
        }
        if (grid[i][j] == 1) {
            grid[i][j] = 2; // Mark the cell as visited
            dfs(grid, i + 1, j, queue);
            dfs(grid, i - 1, j, queue);
            dfs(grid, i, j + 1, queue);
            dfs(grid, i, j - 1, queue);
        } else {
            queue.offer(i * grid[0].length + j); // Add water boundary to the queue
        }
    }



    public static void main(String[] args) {
        ShortestBridge sb = new ShortestBridge();
        int[][] grid = {
                {0, 1},
                {1, 0}
        };

        System.out.println(sb.shortestBridge(grid)); // Output: 1
    }
}
