package com.yifei.mvntest;

/**
 * Created by xuyifei01 on 14-7-22.
 */
public class HelloWorld {
    public String sayHello() {
        return "Hello Maven";
    }
    public static void main(String[] args) {
        System.out.println(new HelloWorld().sayHello());
    }
}
