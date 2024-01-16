package com.leetcode.frequent.elemts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SudokuTest {
    Sudoku sudoku = new Sudoku();

    @Test
//    @MethodSource("provider")
    void test() {
        char[][] elements = new char[][]{
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        Assertions.assertTrue(sudoku.isValidSudoku(elements));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
            }),
            Arguments.of(new char[][]{
                {'8','3','.','.','7','.','.','.','.'}, 
                {'6','.','.','1','9','5','.','.','.'}, 
                {'.','9','8','.','.','.','.','6','.'}, 
                {'8','.','.','.','6','.','.','.','3'}, 
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'}, 
                {'.','6','.','.','.','.','2','8','.'}, 
                {'.','.','.','4','1','9','.','.','5'}, 
                {'.','.','.','.','8','.','.','7','9'}
            })
        );
    }
}
