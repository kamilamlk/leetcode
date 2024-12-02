package com.leetcode.queue.conclusion;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int sColor = image[sr][sc];
        if (sColor != color) {
            dfs(image, sr, sc, color, sColor);
        }
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int color, int sColor) {
        if (sr < 0 || sr >= image.length ||
            sc < 0 || sc >= image[sr].length ||
            image[sr][sc] == color ||
            image[sr][sc] != sColor) {
            return;
        }

        image[sr][sc] = color;
        dfs(image, sr - 1, sc, color, sColor); // top
        dfs(image, sr, sc - 1, color, sColor); // left
        dfs(image, sr, sc + 1, color, sColor); // right
        dfs(image, sr + 1, sc, color, sColor); // bottom
    }

    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();
        int[][] image = new int[][] {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1;
        int sc = 1;
        int newColor = 2;
        int[][] result = floodFill.floodFill(image, sr, sc, newColor);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
