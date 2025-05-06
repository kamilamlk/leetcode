package com.leetcode.ocp.api;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Comparison implements Cloneable {
    static Integer some;
    public static void main(String[] args) throws CloneNotSupportedException {
        var light = new Comparison();
        var copy = light.clone();
        System.out.println(copy == light);
        System.out.println(copy.equals(light));
        Character charObj1   = Character.valueOf('\n');
        Boolean   boolObj1   = Boolean.valueOf(true);
        Integer   intObj1    = Integer.valueOf(2020);
        Double    doubleObj1 = Double.valueOf(3.14);

//        Character charObj2 = 'a'; // autoboxed
//        char charObj3 = 'b'; // primitive
//        System.out.println(charObj2.compareTo(charObj3));
//

        List<Character> chars = List.of('ä', 'a', 'z');
        for (Character aChar : chars) {
            System.out.print((int) aChar + " ");
        }
        System.out.println();
        chars = new ArrayList<>(chars);
        chars.sort(Comparator.naturalOrder());
        System.out.println("Numeric (Unicode) sort: " + chars);

        List<String> sChars = new ArrayList<>(List.of("ä", "a", "z"));

        Collator germanCollator = Collator.getInstance(Locale.GERMAN);
        sChars.sort(germanCollator);

        System.out.println("Locale-aware sort (German): " + sChars);

        System.out.println(Double.compare(10.0, -Double.NaN));


        System.out.println(Integer.toString(-2, 2));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(2));

        System.out.println(Character.toUpperCase('A'));
        int[] array = new int[5];
        array.clone();

        Integer a = 1000;
        Integer b = 1000;
        System.out.println(a == b);

        String s = "A";
        s += "B";
    }
}
