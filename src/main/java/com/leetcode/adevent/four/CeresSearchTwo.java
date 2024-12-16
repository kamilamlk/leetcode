package com.leetcode.adevent.four;

import com.leetcode.adevent.ResourceUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CeresSearchTwo {
    private enum Direction {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    public int search(String[] strings, String target) {
        int counter = 0;
        int mid = target.length() / 2;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length(); j++) {

                if (strings[i].charAt(j) == target.charAt(mid)) {
                    counter += dfs(strings, target, i, j);
                }
            }
        }
        return counter;
    }

    private int dfs(String[] strings, String target, int row, int col) {
        int firstDiagonal = Math.max(
            hasWord(strings, target, row-1, col-1, Direction.TOP_LEFT),
            hasWord(strings, target, row+1, col+1, Direction.BOTTOM_RIGHT)
        );
        int secondDiagonal = Math.max(
            hasWord(strings, target, row-1, col+1, Direction.TOP_RIGHT),
            hasWord(strings, target, row+1, col-1, Direction.BOTTOM_LEFT)
        );

        return firstDiagonal == 1 && secondDiagonal == 1 ? 1 : 0;
    }

    private int hasWord(String[] strings, String target, int row, int col, Direction direction) {
        for (int i = 0; i < target.length(); i++) {
            if (row < 0 || row >= strings.length || col < 0 || col >= strings[row].length()) {
                return 0;
            }
            if (strings[row].charAt(col) != target.charAt(i)) {
                return 0;
            }
            if (direction == Direction.TOP_LEFT) {
                row++;
                col++;
            } else if (direction == Direction.TOP_RIGHT) {
                row++;
                col--;
            } else if (direction == Direction.BOTTOM_LEFT) {
                row--;
                col++;
            } else if (direction == Direction.BOTTOM_RIGHT) {
                row--;
                col--;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        CeresSearchTwo ceresSearch = new CeresSearchTwo();
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
        System.out.println(ceresSearch.search(strings, "MAS"));

        String file = ResourceUtils.readFile("ceres-search.txt");
        List<String> fileDate = new ArrayList<>(Arrays.asList(file.split("\n")));
        System.out.println(ceresSearch.search(
            fileDate.toArray(new String[0]),
            "MAS")
        );
    }
}
