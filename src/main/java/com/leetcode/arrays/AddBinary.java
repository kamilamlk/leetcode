package com.leetcode.arrays;

public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        short carry = 0;
        int ai = a.length() - 1;
        int bi = b.length() - 1;
        while (ai >= 0 || bi >= 0 || carry == 1) {
            short sum = carry;
            if (ai >= 0) {
                sum += (short) (a.charAt(ai--) - '0');
            }
            if (bi >= 0) {
                sum += (short) (b.charAt(bi--) - '0');
            }
            result.append(sum % 2);
            carry = (short) (sum / 2);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("11", "1"));
        System.out.println(addBinary.addBinary("1010", "1011"));
    }
}
