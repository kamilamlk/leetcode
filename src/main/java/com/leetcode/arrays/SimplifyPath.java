package com.leetcode.arrays;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class SimplifyPath {
    /**
     * . - current dir
     * .. - poll / to prev dir
     * //, /// - single slash - do nothing
     *
     *
     */
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        int i = 0;
        while (i < path.length()){
            // skip all leading slashes
            if (path.charAt(i) == '/') {
                i++;
                continue;
            }

            int j = i;
            // till next slash
            while (j < path.length() && path.charAt(j) != '/') {
                j++;
            }
            String dir = path.substring(i, j);
            i = j - 1;
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (!dir.equals(".")){
                stack.offer(dir);
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();

        for (String s : stack) {
            sb.append('/').append(s);
        }
        if (sb.isEmpty()) {
            return "/";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
//        String path = "/a/./b/../../c/";
//        System.out.println(sp.simplifyPath(path)); // Output: "/c"
//        path = "/../";
//        System.out.println(sp.simplifyPath(path)); // Output: "/"
//        path = "/home//foo/";
//        System.out.println(sp.simplifyPath(path)); // Output: "/home/foo"

        System.out.println(sp.simplifyPath("/home/user/Documents/../Pictures")); // Output: "/home/user/Pictures"
    }
}
