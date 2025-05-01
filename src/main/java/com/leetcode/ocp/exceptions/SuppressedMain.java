package com.leetcode.ocp.exceptions;

import java.io.IOException;

public class SuppressedMain {
    static class MyResource implements AutoCloseable {
        public void close() throws Exception {
            System.out.println("Closing resource");

        }
    }

    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            System.out.println("Using resource");
           // throw new IOException("Main exception");
        } catch (Exception e) {
            System.out.println("Caught exception: " + e);
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println(suppressed.getMessage());
            }
        } finally {
            System.out.println("Finally block executed");
        }

    }

}
