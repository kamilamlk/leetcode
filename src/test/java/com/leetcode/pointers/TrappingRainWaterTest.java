package com.leetcode.pointers;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TrappingRainWaterTest {
    TrappingRainWater water = new TrappingRainWater();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] height, int expected) {
        Assertions.assertEquals(expected, water.trap(height));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}, 6),
                Arguments.of(new int[] {4,2,0,3,2,5}, 9)
        );
    }
}
