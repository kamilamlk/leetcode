package com.leetcode.pointers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DuplicatesTest {
    Duplicates duplicates = new Duplicates();

    @ParameterizedTest
    @MethodSource("provider")
    void shouldRemoveDuplicates(int[] nums, int expected) {
        int result = duplicates.removeDuplicates(nums);
        assertEquals(expected, result);
        for (int num : nums) {
            System.out.println(num);
        }

        var expectedArray = new int[expected];
        System.arraycopy(nums, 0, expectedArray, 0, expected);

        Assertions.assertThat(expectedArray)
            .doesNotHaveDuplicates();
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(new int[]{1,1,2}, 2)
        );
    }
}