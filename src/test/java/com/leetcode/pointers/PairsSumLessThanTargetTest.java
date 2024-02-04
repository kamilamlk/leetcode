package com.leetcode.pointers;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PairsSumLessThanTargetTest {
    PairsSumLessThanTarget pairsSumLessThanTarget = new PairsSumLessThanTarget();

    @ParameterizedTest
    @MethodSource("provider")
    void test(List<Integer> nums, int target, int expected) {
        Assertions.assertEquals(expected, pairsSumLessThanTarget.countPairs(new ArrayList<>(nums), target));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(List.of(-1,1,2,3,1), 2, 3),
                Arguments.of(List.of(-6,2,5,-2,-7,-1,3), -2, 10),
                Arguments.of(List.of(9,-5,-5,5,-5,-4,-6,6,-6), 3, 27)
        );
    }
}
