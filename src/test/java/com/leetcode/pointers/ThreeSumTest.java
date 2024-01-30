package com.leetcode.pointers;

import org.junit.jupiter.api.Test;

public class ThreeSumTest {
    ThreeSum threeSum = new ThreeSum();

    @Test
    void test() {
        int[] array = new int[] {-1,0,1,2,-1,-4};

        System.out.println(threeSum.threeSum(array));
    }
}
