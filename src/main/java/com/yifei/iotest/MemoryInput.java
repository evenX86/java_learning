package com.yifei.iotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 从内存输入
 * 面向字符
 * Created by xuyifei01 on 14-7-25.
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("F:\\xuyifei\\java_learning\\src\\main\\java\\com\\yifei\\iotest\\MemoryInput.java"));
        int c;
        while ((c = in.read())!=-1) {
            System.out.println((char)c);
        }
    }
}
