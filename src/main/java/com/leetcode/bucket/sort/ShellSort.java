package com.leetcode.bucket.sort;

public class ShellSort {
    public void sort(int[] array) {
        int n = array.length;
        // defining gap
        for (int gap = n /2; gap > 0; gap /=2 ){
            // i is a starting point for iterations
            // j is decrementing the gap with every swap of elements.
            // This allows us to switch elements that are far apart for gap
            for (int i = gap; i < array.length ; i++) {
                int temp = array[i];
                // swap happens using j. i is a reference for starting point
                int j = i;
                while(j >= gap && array[j-gap] > temp) {
                    // swap
                    array[j] = array[j-gap];
                    array[j-gap] = temp;
                    // decrement j by gap
                    j-=gap;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 11, 9, 7, 2, 8, 0, 4, 6, 13};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(array);
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
