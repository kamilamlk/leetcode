package com.leetcode.string;

public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        if (n < 3) return false;

        // choose first number: from [0..i]
        for (int i = 1; i <= n - 2; i++) {
            // avoid leading zero unless the number itself is "0"
            if (num.charAt(0) == '0' && i > 1) break;
            long num1 = Long.parseLong(num.substring(0, i));

            // choose second number: from [i..j]
            for (int j = i + 1; j <= n - 1; j++) {
                if (num.charAt(i) == '0' && j > i + 1) break;
                long num2 = Long.parseLong(num.substring(i, j));

                // now check if the rest of the string follows the additive property
                if (isAdditive(num1, num2, j, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAdditive(long num1, long num2, int start, String num) {
        if (start == num.length()) return true;

        long sum = num1 + num2;
        String sumStr = Long.toString(sum);

        // check if the next part of the string matches the sum
        if (!num.startsWith(sumStr, start)) return false;

        return isAdditive(num2, sum, start + sumStr.length(), num);
    }

    public static void main(String[] args) {
        AdditiveNumber solver = new AdditiveNumber();
        //System.out.println(solver.isAdditiveNumber("112358")); // Output: true
        //System.out.println(solver.isAdditiveNumber("199100199")); // Output: true
        System.out.println(solver.isAdditiveNumber("9999999999999999999"));
    }
}
