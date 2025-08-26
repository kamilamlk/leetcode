package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = 0;
        int m = 0;
        int s1Idx = 0;
        int s2Idx = 0;
        int i = 0;
        while (i < s3.length()) {
            int prev = i;
            while(i < s3.length() && s1Idx < s1.length() && s3.charAt(i) == s1.charAt(s1Idx)) {
                i++;
                s1Idx++;
            }

            n++;
            prev = i;

            while(i < s3.length() && s2Idx < s2.length() && s3.charAt(i) == s2.charAt(s2Idx)) {
                i++;
                s2Idx++;
            }
            if(i < s3.length() && i == prev) {
                return false;
            }
            m++;
        }

        return Math.abs(n - m) <= 1;
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("10"));
        InterleavingString interleavingString = new InterleavingString();
//        System.out.println(interleavingString.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
//        System.out.println(interleavingString.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
//        System.out.println(interleavingString.isInterleave("", "", ""));
//        System.out.println(interleavingString.isInterleave("a", "", "c"));
        System.out.println(interleavingString.isInterleave("a", "b", "a"));
    }
}
