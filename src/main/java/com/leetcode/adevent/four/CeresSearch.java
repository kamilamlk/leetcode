package com.leetcode.adevent.four;

import com.leetcode.adevent.ResourceUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CeresSearch {
    private enum Direction {
        LEFT, RIGHT, UP, DOWN, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    public int search(String[] strings, String target) {
        int counter = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length(); j++) {
                for (Direction value : Direction.values()) {
                    counter+= dfs(strings, target, i, j, value);
                }
            }
        }
        return counter;
    }

    private int dfs(String[] strings, String target, int row, int col, Direction direction) {
        switch (direction) {
            case LEFT: {
                for (int i = 0; i < target.length(); i++) {
                    if (col >= strings[row].length()) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    col++;
                }
                return  1;
            } case RIGHT: {
                for (int i = 0; i < target.length(); i++) {
                    if (col < 0) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    col--;
                }
                return 1;
            } case UP: {
                for (int i = 0; i < target.length(); i++) {
                    if (row < 0) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    row--;
                }
                return 1;
            } case DOWN: {
                for (int i = 0; i < target.length(); i++) {
                    if (row >= strings.length) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    row++;
                }
                return 1;
            } case TOP_LEFT: {
                for (int i = 0; i < target.length(); i++) {
                    if (row >= strings.length || col >= strings[row].length()) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    row++;
                    col++;
                }
                return 1;
            } case TOP_RIGHT: {
                for (int i = 0; i < target.length(); i++) {
                    if (row >= strings.length || col < 0) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    row++;
                    col--;
                }
                return 1;
            } case BOTTOM_LEFT: {
                for (int i = 0; i < target.length(); i++) {
                    if (row < 0 || col >= strings[row].length()) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    row--;
                    col++;
                }
                return 1;
            } case BOTTOM_RIGHT: {
                for (int i = 0; i < target.length(); i++) {
                    if (row < 0 || col < 0) {
                        return 0;
                    }
                    if (strings[row].charAt(col) != target.charAt(i)) {
                        return 0;
                    }
                    row--;
                    col--;
                }
                return 1;
            }
        }
        return 0;
    }

    
    public static void main(String[] args) throws IOException, URISyntaxException {
        CeresSearch ceresSearch = new CeresSearch();
        String[] strings = new String[] {
            "MMMSXXMASM",
            "MSAMXMSMSA",
            "AMXSXMAAMM",
            "MSAMASMSMX",
            "XMASAMXAMM",
            "XXAMMXXAMA",
            "SMSMSASXSS",
            "SAXAMASAAA",
            "MAMMMXMMMM",
            "MXMXAXMASX"
        };
        System.out.println(ceresSearch.search(strings, "XMAS"));

        String file = ResourceUtils.readFile("ceres-search.txt");
        List<String> fileDate = new ArrayList<>(Arrays.asList(file.split("\n")));
        System.out.println(ceresSearch.search(
            fileDate.toArray(new String[0]),
            "XMAS")
        );
    }
}
