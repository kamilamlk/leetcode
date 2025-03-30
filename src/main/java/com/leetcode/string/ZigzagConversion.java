package com.leetcode.string;

import org.junit.gen5.api.Assertions;

public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] array = new char[s.length()];
        int i = 0;
        int length = s.length();
        int circle = numRows * 2 - 2;

        for(int row = 0; row < numRows; row++) {
            int k = row;
            if(row == 0 || row == numRows - 1) {
                while(i < length && k < length) {
                    array[i] = s.charAt(k);
                    k+=circle;
                    i++;
                }
            } else {
                int end = circle;
                while(i < length && k < length) {
                    array[i++] = s.charAt(k);
                    if(k % circle < numRows) {
                        k = end - row;
                    } else {
                        k = end + row;
                        end += circle;
                    }
                }
            }
        }

        return new String(array);
    }

    public static void main(String[] args) {
        ZigzagConversion conversion = new ZigzagConversion();
        System.out.println(conversion.convert("AB", 1));
        Assertions.assertEquals(conversion.convert("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
        Assertions.assertEquals(conversion.convert("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
        Assertions.assertEquals(conversion.convert("A", 1), "A");
        Assertions.assertEquals(conversion.convert("AB", 1), "AB");
        System.out.println(conversion.convert("ABCD", 2));
    }
}
