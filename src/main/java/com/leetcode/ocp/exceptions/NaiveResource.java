package com.leetcode.ocp.exceptions;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NaiveResource {
    public static void main(String[] args) throws IOException {
        var fis = new FileReader(args[0]);
        try (fis; var br = getBr(fis)) {
            String textLine = br.readLine();

            if (textLine != null) {
                System.out.println(textLine);
            } else throw new EOFException("End of file reached");
        } finally {
            System.out.println("Finally block executed");
        }

        System.out.println(fis.ready());
    }

    private static BufferedReader getBr(FileReader fis) {
        throw new RuntimeException();
    }
}
