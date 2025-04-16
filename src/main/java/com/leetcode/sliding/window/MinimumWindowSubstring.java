package com.leetcode.sliding.window;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] tCounts = new int[58];
        int[] sCounts = new int[58];

        for (int i = 0; i < t.length(); i++) {
            tCounts[t.charAt(i) - 'A']++;
        }

        int size = t.length();
        int left = 0;
        int minLeft = 0;
        int minRight = s.length() + 1;
        int count = 0;
        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);
            sCounts[rChar - 'A']++;
            if (sCounts[rChar - 'A'] <= tCounts[rChar - 'A']) {
                count++;
            }

            while(count >= size) {
                if (count == size && right - left + 1 == size) {
                    return s.substring(left, right + 1);
                }

                if (right - left + 1 < minRight - minLeft + 1) {
                    minLeft = left;
                    minRight = right;
                }

                char lChar = s.charAt(left);
                if (sCounts[lChar - 'A'] <= tCounts[lChar - 'A']) {
                    count--;
                }
                sCounts[lChar - 'A']--;
                left++;
            }
        }
        if (minRight == s.length() + 1) {
            return "";
        }
        return s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minimumWindowSubstring.minWindow("a", "a"));
        System.out.println(minimumWindowSubstring.minWindow("a", "aa"));
    }
}
