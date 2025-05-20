package com.leetcode.ocp.api;

public class Outer {
    static Integer staticField = 10;
    Integer instanceField = 20;

    static class Inner {
        public void display() {
            System.out.println("Inner class method: " + staticField);
        }
    }

    class NonStaticInner {
        public NonStaticInner() {
            System.out.println(instanceField);
        }

        public void display() {
            System.out.println("Non-static inner class method: " + instanceField);
        }
    }

    public void outerMethod() {
        int x = 5;
        int y = 10;

        class LocalHelper {
            public void display() {
                System.out.println("Local class method: " + (x + y));
                System.out.println("Local class method: " + Outer.this.instanceField);
            }
        }
        new LocalHelper().display();
    }
}
