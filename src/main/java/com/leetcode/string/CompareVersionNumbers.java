package com.leetcode.string;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split(".");
        for(int i = 0; i < Math.max(v1.length, v2.length); i++) {
            int v1Num = i < v1.length ? parse(v1[i]) : 0;
            int v2Num = i < v2.length ? parse(v2[i]) : 0;
            if(v1Num < v2Num) return -1;
            if(v1Num > v2Num) return 1;
        }
        return 0;
    }

    private int parse(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        CompareVersionNumbers compareVersionNumbers = new CompareVersionNumbers();
//        System.out.println(compareVersionNumbers.compareVersion("1.01", "1.001"));
//        System.out.println(compareVersionNumbers.compareVersion("1.0", "1.0.0"));
//        System.out.println(compareVersionNumbers.compareVersion("0.1", "1.1"));
//        System.out.println(compareVersionNumbers.compareVersion("1.0.1", "1"));
        System.out.println(compareVersionNumbers.compareVersion("1.2", "1.10"));

        System.out.println((4.0 / 333.0));
    }
}
