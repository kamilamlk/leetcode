package com.leetcode.pointers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class SortColorTest {
    SortColor sortColor = new SortColor();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] array, int[] expected) {
        sortColor.sortColors(array);
        System.out.println(Arrays.toString(array));
        Assertions.assertThat(array)
            .containsExactly(expected);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(new int[] {2,0,2,1,1,0}, new int[]{0, 0, 1, 1, 2, 2}),
            Arguments.of(new int[] {2,0,1}, new int[]{0,1,2})
        );
    }
}
