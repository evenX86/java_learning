package com.yifei.mvntest;

import junit.framework.TestCase;

/**
 * Created by xuyifei01 on 14-7-23.
 */
public class HelloWorldTest extends TestCase {
    public void testSayHello() throws Exception {
        HelloWorld helloWorld = new HelloWorld();
        String result = helloWorld.sayHello();
        assertEquals("Hello Maven",result);
    }
}
