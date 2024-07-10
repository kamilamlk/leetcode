package com.leetcode.pointers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ContainerWithMostWaterTest {
    ContainerWithMostWater container = new ContainerWithMostWater();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] height, int expected) {
        Assertions.assertEquals(expected, container.maxArea(height));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(new int[] {1,8,6,2,5,4,8,3,7}, 49),
            Arguments.of(new int[] {0,2}, 0),
            Arguments.of(new int[] {1,2,4,3}, 4)
        );
    }
}
