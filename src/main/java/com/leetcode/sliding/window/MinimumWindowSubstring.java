package com.leetcode.sliding.window;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) {
            return "";
        }

        int[] counts = new int[58];
        for(int i = 0; i < t.length(); i++) {
            char current = t.charAt(i);
            counts[current - 'A']++;
        }

        int[] sCounts = new int[58];
        int left = 0;
        int minLeft = 0;
        int minRight = s.length() - 1;

        int unique = 0;

        for(int right = 0; right < s.length(); right++){
            char current = s.charAt(right);
            sCounts[current - 'A']++;
            if(counts[current - 'A'] != 0 && sCounts[current - 'A'] <= counts[current - 'A']) {
                unique++;
            }

            while(sCounts[current - 'A'] > counts[current - 'A']) {
                char leftChar = s.charAt(left);
                if(counts[leftChar - 'A'] != 0 && sCounts[leftChar - 'A'] <= counts[leftChar - 'A']) {
                    unique--;
                }
                sCounts[leftChar - 'A']--;
                left++;
            }

            int diff = right - left + 1; // length
            if(diff == t.length() && unique == t.length()) {
                return s.substring(left, right + 1);
            }

            if(diff > t.length() && unique == t.length() && (minRight - minLeft + 1) > diff) {
                minRight = right;
                minLeft = left;
            }
        }

        return s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("ADOBANC", "ABC"));
    }
}
