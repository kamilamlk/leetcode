package com.leetcode.arrays.and.hashing.duplicate_217;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ContainsDuplicateTest {
    private ContainsDuplicate containsDuplicate = new ContainsDuplicate();

    @ParameterizedTest
    @MethodSource("provideArguments")
    public void test(int[] array, boolean result) {
        Assertions.assertEquals(result, containsDuplicate.containsDuplicates(array));
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new int[]{1,2,3,1}, true),
                Arguments.of(new int[]{1,2,3,4}, false),
                Arguments.of(new int[]{1,1,1,3,3,4,3,2,4,2}, true),
                Arguments.of(new int[]{45,1,1,3,3,4,3,2,4,45}, true)
        );
    }
}
