package com.yifei.thread;

import com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程局部变量分别为每个线程存储了各自的属性值，并提供给每个线程使用，可以使用get读取
 * set来设置，如果是第一次调用的话initialValue就会被自动调用。
 * Created by xuyifei01 on 14-8-1.
 */
public class SafeTask implements Runnable {
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.printf("Starting Thread : %s : %s\n", Thread.currentThread().getId(), startDate.get());
        try {
            TimeUnit.SECONDS.sleep((long) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished : %s:%s\n", Thread.currentThread().getId(), startDate.get());
    }
    public static void main(String[] args) {
        SafeTask safeTask = new SafeTask();
        for (int i=0;i<3;i++) {
            Thread thread = new Thread(safeTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
