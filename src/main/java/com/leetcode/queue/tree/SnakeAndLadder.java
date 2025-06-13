package com.leetcode.queue.tree;
// LeetCode Problem: https://leetcode.com/problems/snakes-and-ladders/
import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadder {
    public int snakesAndLadders(int[][] board) {
        return bfs(board);
    }

    private int bfs(int[][] board) {
        int n = board.length;
        int total = n * n;
        boolean[] visited = new boolean[total + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int position = queue.poll();
                if (position == total) return moves;

                for (int dice = 1; dice <= 6 && position + dice <= total; dice++) {
                    int next = position + dice;
                    int[] coords = getCoordinates(next, n);
                    int row = coords[0], col = coords[1];

                    int destination = board[row][col] == -1 ? next : board[row][col];
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    private int[] getCoordinates(int position, int n) {
        int row = n - 1 - (position - 1) / n;
        int col = (position - 1) % n;
        if ((n - row) % 2 == 0) {
            col = n - 1 - col;
        }
        return new int[]{row, col};
    }

    public static void main(String[] args) {
        SnakeAndLadder solver = new SnakeAndLadder();
        int[][] board = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        };
        System.out.println("Minimum moves to reach the end: " + solver.snakesAndLadders(board));
    }
}