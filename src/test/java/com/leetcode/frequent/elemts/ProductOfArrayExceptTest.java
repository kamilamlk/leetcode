package com.leetcode.frequent.elemts;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ProductOfArrayExceptTest {
    ProductOfArrayExcept solution = new ProductOfArrayExcept();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] array, int[] expected) {
        int[] result = solution.productExceptSelf(array);

        for (int i = 0; i < result.length; i++) {
            Assertions.assertEquals(result[i], expected[i]);
        }
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(new int[]{1,2,3,4}, new int[]{24,12,8,6}),
            Arguments.of(new int[]{-1,1,0,-3,3}, new int[]{0,0,9,0,0})
        );
    }
}
