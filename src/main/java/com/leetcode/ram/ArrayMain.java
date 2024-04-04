package com.leetcode.ram;

import java.util.Date;

public class ArrayMain {

    public static void main(String[] args) {
        final int data = 1_000_000;
        final int capacity = 1000;
//        DArray<Integer> dArray = new DArray<>()
//        long dStart = Instant.now().getEpochSecond();
//        for (int i = 0; i < data; i++) {
//            dArray.add(i, i);
//        }
//        long dEnd = Instant.now().getEpochSecond();
//        System.out.println("D-time: " + (dEnd - dStart));

        BArray<Integer> bArray = new BArray<>(capacity, capacity);
        long bStart = new Date().getTime();
//        for (int i = 0; i < data; i++) {
//            bArray.add(i, i);
//        }
        long bEnd = new Date().getTime();
        System.out.println("b-time: " + (bEnd - bStart));

//        long bRemove = Instant.now().getEpochSecond();
//        bArray.remove(0);
//        long bRemoveEnd = Instant.now().getEpochSecond();
//        System.out.println("b remove end: " + (bRemoveEnd - bRemove));

        IArray<Integer> iArray = new IArray<>(capacity);
        long iStart = new Date().getTime();;
        for (int i = 0; i < data; i++) {
            iArray.add(i, i);
        }
        long iEnd = new Date().getTime();
        System.out.println("i-time: " + (iEnd - iStart));
    }
}
