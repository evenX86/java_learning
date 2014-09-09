package com.yifei.ConcurrencyCookbook.customizing;

import com.yifei.thread.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei01 on 14-9-9.
 */
public class MyTask implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");
        MyTask task = new MyTask();

        Thread thread  = myFactory.newThread(task);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread);
    }
}
