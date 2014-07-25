package com.yifei.iotest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by xuyifei01 on 14-7-25.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("F:\\xuyifei\\java_learning\\src\\main\\java\\com\\yifei\\iotest\\MemoryInput.java").getBytes()));
            while (true) {
                System.out.println((char)in.readByte());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
