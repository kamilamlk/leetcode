package com.leetcode.ocp.exceptions;

public class Main {
    public static void main(String[] args) throws Throwable {
        doException();
    }

    private static void doException() {
        try {
            throw new Error();
        } catch (Exception e) {
            System.out.println("Exception caught :" + e.toString());
        } catch (Error e) {
            System.out.println("Error caught :" + e.toString());
        } finally {
            System.out.println("Finally block executed : ");
        }
    }
}
