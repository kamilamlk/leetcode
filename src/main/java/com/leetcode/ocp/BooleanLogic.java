package com.leetcode.ocp;

public class BooleanLogic {
    public static void main(String[] args) {
        System.out.println(2 | 1);

        int a = 5, b = 5, c = 4;
        System.out.println(a == b ^ a == ++c );
        System.out.println(c);

        char v1 = ')'; // 41
        byte v2 = 13;
        int result1 = v1 & v2;
        int result2 = v1 | v2;
        int result3 = v1 ^ v2;
        int result4 = ~v1;
        System.out.println(result1 + " - " + result2 + " - " + result3 + " - " + result4);
        System.out.println(Integer.toBinaryString(v1) + " - " + Integer.toBinaryString(v2));
        System.out.println("1 : " + Integer.toBinaryString(result1));
        System.out.println("2 : " + Integer.toBinaryString(result2));
        System.out.println("3 : " + Integer.toBinaryString(result3));
        System.out.println("4 : " + Integer.toBinaryString(result4));

        // check if bit is set
        int value = 0b0101; // 5
        int mask = 0b0001;  // mask to check bit 0

        boolean isSet = (value & mask) != 0;
        System.out.println(isSet); // true
        // set a bit to one
        mask = 0b1010;
        value |= mask;
        System.out.println(Integer.toBinaryString(value));

        value = 0b0111; // 7
        mask = ~(0b0011); // mask to clear bit 0 => 1110

        int result = value & mask; // => 0110
        System.out.println(Integer.toBinaryString(result)); // "110"
    }
}
