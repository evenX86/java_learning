package com.yifei.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock实现同步
 * - 支持更灵活的同步代码块结构
 * - 提供更多的功能
 * - 允许分离读和写分离
 * - 具有更好的性能
 * Created by xuyifei01 on 14-8-3.
 */
public class PrintQueue {
    private final Lock queueLock = new ReentrantLock();    //声明一个锁对象。

    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }


}
