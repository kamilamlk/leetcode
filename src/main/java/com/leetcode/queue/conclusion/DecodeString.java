package com.leetcode.queue.conclusion;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {
    public String decodeString(String s) {
        char[] array = s.toCharArray();
        Deque<Character> deque = new LinkedList<>();
        StringBuilder builder = new StringBuilder();

        for (char c : array) {
            if ( c == ']') {
                StringBuilder temp = new StringBuilder();
                while (deque.peek() != '[') {
                    temp.append(deque.pop());
                }
                deque.pop();

                StringBuilder digits = new StringBuilder();
                while (!deque.isEmpty() && Character.isDigit(deque.peek())) {
                    digits.append(deque.pop());
                }
                int k = Integer.parseInt(digits.reverse().toString());
                String str = temp.reverse().toString();
                for (int i = 0; i < k; i++) {
                    for (char ch : str.toCharArray()) {
                        deque.push(ch);
                    }
                }
            } else {
                deque.push(c);
            }
        }

        while (!deque.isEmpty()) {
            builder.append(deque.pop());
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeString("3[a]2[bc]")); // return "aaabcbc"
        System.out.println(decodeString.decodeString("3[a2[c]]")); // return "accaccacc"
        System.out.println(decodeString.decodeString("2[abc]3[cd]ef")); // return "abcabccdcdcdef"
        System.out.println(decodeString.decodeString("100[leetcode]"));
    }
}
