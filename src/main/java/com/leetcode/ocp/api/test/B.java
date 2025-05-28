package com.leetcode.ocp.api.test;

public class B extends A {
    int val = 1;

    public B() {
        super(2);
    }

    public void doIt() {

    }

    class C extends A {
        int val = 3;
        C() {
           super(4);
            System.out.println(B.this.val); // 2
            System.out.println(val); // is it hiding?
            System.out.println(C.this.val); // 4
            System.out.println(super.val); // 4
        }

        public void doIt() {

        }
    }
}
