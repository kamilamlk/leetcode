package com.leetcode.pointers;

import org.junit.jupiter.api.Test;

public class FourSumTest {
    FourSum fourSum = new FourSum();

    @Test
    void test() {
        int[] array = new int[] {1,0,-1,0,-2,2};
        System.out.println(fourSum.fourSum(array, 0));
    }
}
