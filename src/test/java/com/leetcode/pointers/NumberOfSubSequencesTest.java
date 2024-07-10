package com.leetcode.pointers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class NumberOfSubSequencesTest {
    NumberOfSubsequences numberOfSubsequences = new NumberOfSubsequences();

    @ParameterizedTest
    @MethodSource("provider")
    void shouldCount(int[] nums, int target, int expected) {
        int result = numberOfSubsequences.numSubseq(nums, target);
        Assertions.assertThat(result).isEqualTo(expected);
    }

//    [3] -> Min value + max value <= target (3 + 3 <= 9)
//    [3,5] -> (3 + 5 <= 9)
//    [3,5,6] -> (3 + 6 <= 9)
//    [3,6] -> (3 + 6 <= 9)
    private static Stream<Arguments> provider() {
        return Stream.of(
//            Arguments.of(new int[]{3,3,6,8}, 10, 6),
//            Arguments.of(new int[]{2,3,3,4,6,7}, 12, 61),
            Arguments.of(new int[]{3,5,6,7}, 9, 4)
        );
    }
}