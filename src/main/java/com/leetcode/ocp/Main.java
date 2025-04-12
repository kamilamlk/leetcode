package com.leetcode.ocp;

public class Main {
    public static void main(String[] args) {

    /*
        System.out.println((Integer.MAX_VALUE + 1L));
        System.out.println((long) (Short.MAX_VALUE + 1));
        System.out.println((long) (Double.MAX_VALUE + 1));
        System.out.println((Long.MAX_VALUE + 1.0d));

        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println((-4.0 / 1.0));
        System.out.println((4.0 / -1.0));

        System.out.println();
        System.out.println(Math.copySign(1.0, -0.0)); // -1.0
        System.out.println(Math.copySign(0.0, -0.0)); // -0.0

        System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(+0.0))); // 0x0
        System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(-0.0))); // 0x8000000000000000

        System.out.println(1.0 / 0.0);  // Infinity
        System.out.println(0.0 / 0.0);  // Infinity

        System.out.println(Math.sqrt(1.0));

        System.out.println(1.0 < Double.NaN);



        double subnormal = 1e-308;
        System.out.println(subnormal / 2); // Prints: 0.0 (underflow!)

        int i1 = 4 / 5; // 0
        System.out.println(i1);
        int i2 = 8 / 8; // 1
        System.out.println(i2);
        double d1 = (double)12 / 8; // 1
        System.out.println(d1);

*/

        double dr0 = -7.0 % 5L;
        System.out.println(dr0);

        byte b = 3;
        byte b2 = 4;
        byte b3 = (byte) (b + b2);

        short h = 40;
        h = (short) (h+2);

        short trademakr = 100 + '%' + 100;
        System.out.println("Some: "+ 2 * 2);

        int j = 10;
        j = ++j;
        System.out.println(j);


        double x = 4.5;
        x = x + ++x;
        System.out.println(x);

        double hours = 45;
        Double time = 18.0;
        boolean overtime = hours >= 45;
        System.out.println(overtime);

        System.out.println(1.0 - 2.0 / 3.0 == 1.0 / 3.0);
    }


}
