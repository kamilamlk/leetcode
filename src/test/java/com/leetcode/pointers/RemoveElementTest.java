package com.leetcode.pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class RemoveElementTest {
    RemoveElement removeElement = new RemoveElement();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] nums, int val, int[] expectedNums) {
        int k = removeElement.removeElement(nums, val);
        System.out.printf("Time complexity: %d", removeElement.timeComplexity);
        assert k == expectedNums.length;
        Arrays.sort(nums, 0, k); // Sort the first k elements of nums
        for (int i = 0; i < expectedNums.length; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new int[] {3,2,2,3}, 3, new int[] {2,2}),
                Arguments.of(new int[] {0,1,2,2,3,0,4,2}, 2, new int[] {0,0,1,3,4})
        );
    }
}
