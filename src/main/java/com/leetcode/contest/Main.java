package com.leetcode.contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine());
        sb = new StringBuilder();
        backtrack(n, 0, 0);
    }

    private static void backtrack(int n, int open, int close) {
        if (sb.length() == n * 2) {
            System.out.println(sb);
            return;
        }

        if (open < n) {
            sb.append('(');
            backtrack(n, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtrack(n, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
