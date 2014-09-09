package com.yifei.ConcurrencyCookbook.customizing;

import java.util.concurrent.ThreadFactory;

/**
 * Created by xuyifei01 on 14-9-9.
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = 1;
    }

    @Override
    public Thread newThread(Runnable r) {
        MyThread mythread = new MyThread(r,prefix+"-"+counter);
        counter++;
        return  mythread;

    }
}
