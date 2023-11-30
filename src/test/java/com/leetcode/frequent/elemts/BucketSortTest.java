package com.leetcode.frequent.elemts;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class BucketSortTest {
    BucketSort bucketSort = new BucketSort();
    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] array, int[] expected) {
        bucketSort.sort(array, 5);
        Assertions.assertTrue(Arrays.equals(expected, array));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new int[]{5, 9, 6, 4, 6}, new int[]{4, 5, 6, 6, 9})
        );
    }
}
