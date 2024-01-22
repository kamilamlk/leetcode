package com.leetcode.pointers;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ValidPalindromeTest {
    ValidPalindrome validPalindrome = new ValidPalindrome();


    @ParameterizedTest
    @MethodSource("provider")
    void test(String s, boolean expected) {
        Assertions.assertEquals(expected, validPalindrome.isPalindrome(s));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("A man, a plan, a canal: Panama", true),
            Arguments.of("race a car", false),
            Arguments.of(" ", true),
            Arguments.of("0P", false)
        );
    }
}
