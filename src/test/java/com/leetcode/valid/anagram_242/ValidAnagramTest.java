package com.leetcode.valid.anagram_242;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ValidAnagramTest {
    private final ValidAnagram anagram = new ValidAnagram();

    @ParameterizedTest
    @MethodSource("provider")
    void test(String s, String t, boolean expected) {
        Assertions.assertEquals(expected, anagram.isAnagram(s, t));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of("anagram", "nagaram", true),
                        Arguments.of("rat", "car", false)
        );
    }
}
