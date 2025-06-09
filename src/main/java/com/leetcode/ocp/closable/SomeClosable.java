package com.leetcode.ocp.closable;

public class SomeClosable implements java.io.Closeable {

    @Override
    public void close() {
        // Custom close logic here
        System.out.println("SomeClosable is being closed.");
    }

    public void doSomething() {
        System.out.println("Doing something in SomeClosable.");
    }

    public static void main(String[] args) {
        try(var closable = new SomeClosable()) {
            closable.doSomething();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed.");
        }
    }
}
