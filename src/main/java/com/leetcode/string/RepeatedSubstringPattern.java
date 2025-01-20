package com.leetcode.string;

public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        int subEnd = 0;
        int subStart = 0;
        for(int i = 1; i < s.length(); i++) {

        }
        return true;
    }


    private boolean proccess() {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                System.out.println("RuntimeException");
                return true;
            } else {
                System.out.println("Exception");
            }
        } finally {
            System.out.println("Finally");
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new RepeatedSubstringPattern().proccess());
    }
}
