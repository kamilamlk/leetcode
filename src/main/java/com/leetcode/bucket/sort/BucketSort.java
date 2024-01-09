package com.leetcode.bucket.sort;

public class BucketSort {
    public static void bucketSort(int[] array) {
        int[] bucket = new int[10];

        for (int i = 0; i < array.length; i++) {
            bucket[array[i]]++;
        }

        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            for (int k = 0; k < bucket[j]; k++) {
                array[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 11, 9, 7, 2, 8, 0, 4, 6};

        bucketSort(array);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
