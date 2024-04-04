package com.leetcode.stack;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ValidParenthesesTest {
    ValidParentheses validParentheses = new ValidParentheses();

    @ParameterizedTest
    @MethodSource("provider")
    public void test(String parentheses, boolean expected) {
        Assertions.assertThat(validParentheses.isValid(parentheses))
            .isEqualTo(expected);
    }
    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("()", true),
            Arguments.of("()[]{}", true),
            Arguments.of("(]", false)
        );
    }
}
