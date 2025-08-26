package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> ips = new ArrayList<>();
        backtrack(s, 0, new StringBuilder(), ips, 0);
        return ips;
    }

    private void backtrack(String s, int i, StringBuilder sb, List<String> ips, int slots) {
        if(slots == 4 && i == s.length()) {
            sb.deleteCharAt(sb.length() - 1);
            ips.add(sb.toString());
            return;
        }
        if (slots == 4 || i == s.length()) return;

        int num = 0;
        for(int j = i; j < Math.min(i + 3, s.length()); j++) {
            if (s.charAt(i) == '0' && j > i) {
                break;
            }
            num = num * 10 + (s.charAt(j) - '0');
            if(num < 0 || num > 255) {
                return;
            }
            int len = sb.length();
            sb.append(num).append('.');
            backtrack(s, j + 1, sb, ips, slots + 1);
            sb.setLength(len);
        }
    }

    public static void main(String[] args) {
        RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();
        System.out.println(restoreIPAddresses.restoreIpAddresses("25525511135"));
    }
}
