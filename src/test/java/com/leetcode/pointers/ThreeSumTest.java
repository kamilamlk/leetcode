package com.leetcode.pointers;

import org.junit.jupiter.api.Test;

public class ThreeSumTest {
    ThreeSum threeSum = new ThreeSum();

    @Test
    void test() {
        int[] array = new int[] {0,0,0,0};
        System.out.println(threeSum.threeSum(array));
        array = new int[] {0,0,0};
        System.out.println(threeSum.threeSum(array));
        array = new int[] {-1,0,1,2,-1,-4};
        System.out.println(threeSum.threeSum(array));
        array = new int[] {0,1,1};
        System.out.println(threeSum.threeSum(array));

        array = new int[] {-2,0,1,1,2};
        System.out.println(threeSum.threeSum(array));
    }
}
