package com.leetcode.frequent.elemts;

import org.junit.gen5.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TopKFrequentElementsTest {
    TopKFrequentElements elements = new TopKFrequentElements();

    @Test
    void test() {
        int[] array = new int[]{1,1,1,2,2,3};

        Assertions.assertTrue(Arrays.equals(new int[]{1,2}, elements.topKFrequent(array, 2)));
    }
}
