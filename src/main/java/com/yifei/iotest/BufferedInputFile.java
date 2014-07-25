package com.yifei.iotest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓冲输入文件。
 * 如果打开一个文件用于字符输入，可以使用String或者File作为文件名的FileInputReader
 * 为了提高速度对文件进行缓冲
 * Created by xuyifei01 on 14-7-25.
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine())!=null)
            sb.append(s+"\n");
        in.close();
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        System.out.println(read("F:\\xuyifei\\java_learning\\src\\main\\java\\com\\yifei\\iotest\\BufferedInputFile.java"));
    }
}
