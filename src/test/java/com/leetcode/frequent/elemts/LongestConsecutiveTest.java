package com.leetcode.frequent.elemts;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LongestConsecutiveTest {
    LongestConsecutive longestConsecutive = new LongestConsecutive();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] nums, int expected) {
        Assertions.assertEquals(expected, longestConsecutive.longestConsecutive(nums));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(new int[] {100,4,200,1,3,2}, 4),
            Arguments.of(new int[] {0,3,7,2,5,8,4,6,0,1}, 9)
        );
    }
}
