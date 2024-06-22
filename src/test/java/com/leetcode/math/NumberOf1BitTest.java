package com.leetcode.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class NumberOf1BitTest {
    NumberOf1Bit numberOf1Bit = new NumberOf1Bit();
    @ParameterizedTest
    @MethodSource("provider")
    public void shoutCount(int number, int expected) {
        int result = numberOf1Bit.hammingWeight(number);
        assert result == expected;
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(11, 3),
            Arguments.of(128, 1),
            Arguments.of(2147483645, 30)
        );
    }
}