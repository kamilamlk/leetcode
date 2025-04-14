package com.leetcode.ocp;

public class Arrays {
    public static void main(String[] args) {

        System.out.println(findMin(new int[]{1, 2, 3, 4}));

        Integer a = 5;
        Arrays arrays = new Arrays();
        arrays.magic(a);
        System.out.println(a);
    }

    private static int findMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    private void magic(Integer integer) {
        integer = 7;
    }
}
