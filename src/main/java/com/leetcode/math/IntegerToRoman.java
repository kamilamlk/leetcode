package com.leetcode.math;

public class IntegerToRoman {
    public String intToRoman(int num) {

        int[] values = new int[] {1, 5, 10, 50, 100, 500, 1000};
        int[] digits = new int[] {1, 1, 10, 10, 100, 100, 1000};
        char[] symbs = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

        StringBuilder builder = new StringBuilder();
        while(num > 0) {
            int i = 0;
            while(i < 6 && values[i+1] <= num) {
                i++;
            }
            if(num / digits[i] == 9) {
                builder.append(symbs[i-1]);
                builder.append(symbs[i + 1]);
                num -= digits[i] * 9;
            } else if (num / digits[i] == 4) {
                builder.append(symbs[i]);
                builder.append(symbs[i + 1]);
                num -= digits[i] * 4;
            } else {
                builder.append(symbs[i]);
                num -= values[i];
            }

        }
        return builder.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman intToRoman = new IntegerToRoman();

//        System.out.println(intToRoman.intToRoman(3749));
//        System.out.println(intToRoman.intToRoman(58));
//        System.out.println(intToRoman.intToRoman(1994));
        System.out.println(intToRoman.intToRoman(5));
    }
}
