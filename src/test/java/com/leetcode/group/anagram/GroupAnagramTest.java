package com.leetcode.group.anagram;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GroupAnagramTest {
    GroupAnagram groupAnagram = new GroupAnagram();

    @ParameterizedTest
    @MethodSource("provider")
    void test(String[] array, List<List<String>> expected) {
        for (List<String> strings : groupAnagram.groupAnagrams(array)) {
            // bored to test
            // ....not working
            Assertions.assertTrue(expected.contains(strings));
        }
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(
                        new String[] {"eat","tea","tan","ate","nat","bat"},
                        List.of(
                                List.of("bat"),
                                List.of("nat","tan"),
                                List.of("ate","eat","tea")
                        ))
        );
    }
}
