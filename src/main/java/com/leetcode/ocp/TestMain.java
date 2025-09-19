package com.leetcode.ocp;

public class TestMain {
    static class Example {
        int normalField;
    }

    public static void main(String[] args) throws InterruptedException {
        var example = new Example();
        example.normalField = 42;
        Thread worker = new Thread(() -> {

            example.normalField++;
            System.out.println("Worker thread incremented normalField to: " + example.normalField);
        });

        worker.start();
        Thread.sleep(100);
        System.out.println("Main thread sees normalField as: " + example.normalField);
    }

}