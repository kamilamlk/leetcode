package com.leetcode.ocp.oop.inheritance;

public class Child extends Parent {
    private int anInt;
    private String aString;

    public Child(int anInt, String aString) {
        super(anInt, aString);
    }

    static void staticMethod() {

        System.out.println(Parent.aString);
        System.out.println("Child static");
    }

    @Override
    void instanceMethod() throws InvalidException {
        System.out.println("Child instance");
        throw new InvalidException();
    }

    @Override
    void somMethod(int a) {
        System.out.println(anInt + " " + aString);
        System.out.println(super.anInt + " " + super.aString);
        super.somMethod(a);
    }

    public static void main(String[] args) throws AbstractException {
        Parent p = new Child(1, "a");

        p.staticMethod();   // Which method?

        p.somMethod(1);
    }
}


class AbstractException extends Exception {
    public AbstractException(String message) {
        super(message);
    }
}

class InvalidException extends AbstractException {
    public InvalidException() {
        super("message");
    }
}
