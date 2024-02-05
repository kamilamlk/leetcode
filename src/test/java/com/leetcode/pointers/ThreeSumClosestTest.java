package com.leetcode.pointers;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ThreeSumClosestTest {
    ThreeSumClosest threeSumClosest = new ThreeSumClosest();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] nums, int target, int expected) {
        Assertions.assertEquals(expected, threeSumClosest.threeSumClosest(nums, target));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new int[] {-1,2,1,-4}, 1, 2),
                Arguments.of(new int[] {0,0,0}, 1, 0)
        );
    }
}
