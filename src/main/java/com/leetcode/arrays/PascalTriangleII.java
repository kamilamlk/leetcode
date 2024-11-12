package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prevRow = List.of(1);
        if (rowIndex == 0) {
            return prevRow;
        }


        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> currentRow = new ArrayList<>(i + 1);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i + 1 - 1) {
                    currentRow.add(1);
                } else {
                    currentRow.add(prevRow.get(j - 1) + prevRow.get(j));
                }
            }
            prevRow = currentRow;
        }
        return prevRow;
    }

    public static void main(String[] args) {
        PascalTriangleII pascalTriangle = new PascalTriangleII();
        System.out.println(pascalTriangle.getRow(3));
        System.out.println(pascalTriangle.getRow(0));
        System.out.println(pascalTriangle.getRow(1));
        System.out.println(pascalTriangle.getRow(30));
    }
}
