package com.leetcode.ocp;

public class VarargsDemo {
    public static void main(String[] args) {
        VarargsDemo demo = new VarargsDemo();
        demo.flexiPring();
        demo.flexiPring("a");
        demo.flexiPring("a", "b");
        demo.flexiPring("a", "b", null);
        demo.flexiPring("a", "b", 1);

        Object[] oArray = new Object[] {1, 2, "a"};
        demo.flexiPring(oArray);
        demo.flexiPring(args);
    }

    public static void main(int[] args) {

    }
    private void flexiPring(Object... data) {
        //System.out.println(data.getClass().arrayType());
        for (Object datum : data) {
            System.out.print(datum + " - ");
        }
        System.out.println();
    }
}
