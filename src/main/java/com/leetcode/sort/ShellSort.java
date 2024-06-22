package com.leetcode.sort;

public class ShellSort {
    void sort(int[] arr) {
        int t = 2;
        for(int gap = arr.length/t; gap > 0; gap /= t) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] < arr[j-gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j-gap];
                        arr[j-gap] = temp;
                    }
                }
            }
        }
    }

    void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
        }
    }
}
