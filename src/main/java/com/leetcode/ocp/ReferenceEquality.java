package com.leetcode.ocp;

public class ReferenceEquality {
    public static void main(String[] args) {
        Integer iRef = 10;
        boolean b1 = iRef == null; // object reference equality
        boolean b2 = iRef.equals(null); // object value equality
        boolean b3 = iRef == 10; // primitive value equality
        //boolean b4 = null == 10; // compile error


        String movie1 = new String("The revenge");
        String movie2 = new String("High Noon");
        String movie3 = new String("The revenge");

        boolean test1 = movie1.equals(movie2); // false
        boolean test2 = movie1.equals(movie3); // true
    }
}
