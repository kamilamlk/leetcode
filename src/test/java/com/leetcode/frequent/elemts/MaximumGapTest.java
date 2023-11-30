package com.leetcode.frequent.elemts;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MaximumGapTest {
    private final MaximumGap gap = new MaximumGap();

    @ParameterizedTest
    @MethodSource("provider")
    void test(int[] array, int expected) {
        int result = gap.maximumGap(array);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new int[]{3,6,9,1}, 3),
                Arguments.of(new int[]{10}, 0),
                Arguments.of(new int[]{2,99999999}, 99999997),
                Arguments.of(new int[]{
                        494767408,862126209,213511142,768240025,631263193,
                        839199222,990848796,214568815, 540853864,760724418,
                        980162605,976743981, 168965760,680875496,256709197,
                        970953816, 948680062,347254784,732201399,786249847,
                        782805044,40906641,674241381,784330934,175502610,
                        731105392,424650824,549764101,986090032,710542549,
                        249208107,448419816,527708664,158499246,223421723,
                        114552457,466978424,821491411,19614107,115389497,
                        902211438,2644108,744489871,897895073,372311214,
                        551142753,933294894,426217662,217504874,983488406,
                        516890023,426853110,971124103}, 90545587)
        );
    }
}
