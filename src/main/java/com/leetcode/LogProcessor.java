package com.leetcode;

import org.junit.gen5.commons.util.StringUtils;

public class LogProcessor {
    private String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    public String processLine(String line) {
        var string = line.replaceAll("\\d", "")
            .replace(" ", "_");

        return "LOG" + reverse(string);
    }

    public static void main(String[] args) {
        LogProcessor logProcessor = new LogProcessor();
        System.out.println(logProcessor.processLine("Hello World 123"));
        System.out.println(logProcessor.processLine("12345678"));
    }
}
