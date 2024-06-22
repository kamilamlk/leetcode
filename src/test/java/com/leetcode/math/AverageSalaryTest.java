package com.leetcode.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AverageSalaryTest {
    AverageSalary averageSalary = new AverageSalary();

    @ParameterizedTest
    @MethodSource("provider")
    public void shouldCount(int[] salaries, double expected) {
        double result = averageSalary.average(salaries);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(new int[]{4000,3000,1000,2000}, 2500.00000),
            Arguments.of(new int[]{1000,2000,3000}, 2000.00000)
        );
    }
}