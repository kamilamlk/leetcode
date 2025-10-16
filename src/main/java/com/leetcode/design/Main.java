package com.leetcode.design;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        for (int i = 0; i < 1000; i++) {
            writer.append(i + "\n");
        }
        //writer.close();
    }
}
