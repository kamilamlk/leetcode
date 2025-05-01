package com.leetcode.ocp.exceptions;

import com.leetcode.ocp.A;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static class SuperException extends RuntimeException {
        final String superMessage = "Super Exception";
        public SuperException(String message) {
            super(message);
        }
    }

    static class AException extends SuperException {
        final String a = "Hello";
        public AException(String message) {
            super(message);
        }
    }

    static class BException extends SuperException {
        final String b = "World";
        public BException(String message) {
            super(message);
        }
    }

    static class IntegerDivisionByZero extends RuntimeException {
        final String message = "Integer Division by Zero";
        public IntegerDivisionByZero(String message) {
            super(message);
        }
    }

    public static void methodA(String filename) throws IOException {
        var fis = new FileReader(filename);
        try(BufferedReader br = null) {

        }
    }

    public static void main(String[] args) throws Exception {
        try {
            throw new FileNotFoundException();
        } catch (Exception e) {
            e = new IOException();
            throw e;
        }
    }

    private static void chain() throws Exception {
        try {
            check(1);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (EOFException e) {
            System.out.println("Caught EOFException");
        } catch (IOException e) {
            System.out.println("Caught IOException");
        }
    }

    private static void check(int i) throws Exception {
        try {
            switch (i) {
                case 1 -> throw new FileNotFoundException("File not found");
                case 2 -> throw new IOException("IO Exception");
                default -> System.out.println("OK");
            }
        } catch (Exception e) {
            System.out.println("check: " + e.getMessage());
            e = new Exception("EOF Exception");
            throw e;
        }
    }

    private static void multi_catch() {
        try {
            throw new IOException("");
        } catch (AException | BException e) {
            System.out.println(e.getClass());
        } catch (IntegerDivisionByZero idbz) {
            idbz = new IntegerDivisionByZero("Integer Division by Zero");
        } catch (IOException ioe) {
            ioe = new IOException("IOException");
        } finally {
            System.out.println("Finally block executed");
        }
    }

    private static void doException(int... value) throws RuntimeException {
        try {

            throw new Error();
        } catch (RuntimeException e) {
            System.out.println("Error caught :" + e.toString());
        } finally {
            System.out.println("Finally block executed : ");
            return;
        }
    }
}
