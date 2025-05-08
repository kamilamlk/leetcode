package com.leetcode.ocp.api;

public class StringsTest {
    public static void main(String[] args) {
        String str = "Привет";
        String another = new String("Привет");
        String str2 = "Привет!";
        String str3 = "Привет" + "!";

        System.out.println(str == another.intern());
        System.out.println(str == another);
        System.out.println(str2 == (another + "!"));
        System.out.println(str2 == (str + "!")); // false why?
        System.out.println(str2 == (str3)); // why true?

        StringBuilder sb = new StringBuilder("WOW");

        System.out.println(sb.equals(new StringBuilder(sb).hashCode()));
        System.out.println("WOW".hashCode());

        //System.out.println(Math.addExact(Integer.MAX_VALUE, 1));
        //System.out.println(Math.toIntExact(Long.MAX_VALUE));
        System.out.println(Math.round(-0.5) + Math.round(0.5));
    }
}
