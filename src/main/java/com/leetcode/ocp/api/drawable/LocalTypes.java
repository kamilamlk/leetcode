package com.leetcode.ocp.api.drawable;

public class LocalTypes {
    private int nonStaticField;
    private static int staticField;

    void nonStaticMethod(final int finalParam) {
        int localVar = 10;
        record StaticRecord(int val) {
            //static int field1 = finalParam;
            //static int field2 = localVar;

            static int field3 = staticField;

            void printFieldsFromEnclosignContext() {
//                System.out.println("StaticRecord: " + finalParam);
//                System.out.println("StaticRecord: " + localVar);
//                System.out.println("StaticRecord: " + nonStaticField);
                System.out.println("StaticRecord: " + staticField);
            }
        }
        StaticRecord record = new StaticRecord(5);
        record.printFieldsFromEnclosignContext();
    }
}
