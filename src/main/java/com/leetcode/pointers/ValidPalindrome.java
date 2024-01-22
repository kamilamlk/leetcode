package com.leetcode.pointers;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (isNotChar(s, i)) {
                i++;
                continue;
            }
            if (isNotChar(s, j)) {
                j--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static boolean isNotChar(String s, int j) {
        return (!Character.isAlphabetic(s.charAt(j)) && !Character.isDigit(s.charAt(j))) ;
//        return (s.charAt(j) < 'a' || s.charAt(j) > 'z') && (s.charAt(j) < 'A' || s.charAt(j) > 'Z');
    }
}
