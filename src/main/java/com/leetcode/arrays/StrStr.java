package com.leetcode.arrays;

public class StrStr {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return -1;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();

        for (int i = 0; i < haystackChars.length; i++) {
            for (int j = 0; (j < needleChars.length && i < haystackChars.length); j++) {
                if (haystackChars[i] == needleChars[j]) {
                    if (j == needleChars.length - 1) {
                        return i - j;
                    }
                    i++;
                } else {
                    i = i - j;
                    break;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StrStr strStr = new StrStr();
        System.out.println(strStr.strStr("hello", "ll"));
        System.out.println(strStr.strStr("aaaaa", "bba"));
        System.out.println(strStr.strStr("", ""));
        System.out.println(strStr.strStr("mississippi", "issip"));
    }
}
