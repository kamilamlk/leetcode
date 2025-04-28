package com.leetcode.ocp.records;

public class Main {
    public static void main(String[] args) {
        CD cd = new CD(1);
        CD cd2 = new CD(1);
        System.out.println(cd.equals(cd2));
        System.out.println(cd == cd2);
    }
}
