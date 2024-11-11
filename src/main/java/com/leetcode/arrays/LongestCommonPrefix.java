package com.leetcode.arrays;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        if (strs.length == 0 || strs[0].isEmpty()) {
            return "";
        }
        char currentChar;
        boolean hasCommonPrefix = true;
        int charIndex = 0;
        do {
            currentChar = strs[0].charAt(charIndex);
            for (String str : strs) {
                if (charIndex >= str.length() || str.charAt(charIndex) != currentChar) {
                    hasCommonPrefix = false;
                    break;
                }
            }
            if (hasCommonPrefix) {
                result.append(currentChar);
                charIndex++;
            }
        } while (hasCommonPrefix && charIndex < strs[0].length());
        return result.toString();
    }

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));
        strs = new String[] {"a"};
        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));
        strs = new String[] {"ab", "a"};
        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));
    }
}
