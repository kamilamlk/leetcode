package com.leetcode.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class OddNumbersTest {
    OddNumbers oddNumbers = new OddNumbers();

    @ParameterizedTest
    @MethodSource("provider")
    public void shouldCount(int low, int high, int expected) {
        int result = oddNumbers.countOdds(low, high);
        assert result == expected;
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(3, 7, 3),
            Arguments.of(8, 10, 1)
        );
    }
}