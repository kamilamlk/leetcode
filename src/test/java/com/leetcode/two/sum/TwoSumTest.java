package com.leetcode.two.sum;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoSumTest {
    private final TwoSum twoSum = new TwoSum();

    @ParameterizedTest
    @MethodSource("provider")
    public void test(int[] array, int target, int[] expected) {
        Assertions.assertTrue(Arrays.equals(expected, twoSum.twoSum(array, target)));
    }


    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new int[] {2,7,11,15}, 9, new int[]{0, 1}),
                Arguments.of(new int[] {3,2,4}, 6, new int[]{1, 2}),
                Arguments.of(new int[] {3, 3}, 6, new int[]{0, 1})
        );
    }
}
