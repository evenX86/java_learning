package com.yifei.thread;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 在同步代码中使用条件
 * 数据存储类
 * Created by xuyifei01 on 14-8-3.
 */
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();

    }

    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                
                wait(); //必须在while中调用wait() 并且不断查询while的条件,直到条件为真时候才继续。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d\n", storage.size());
        notifyAll();//唤醒挂起的线程，并且再次检查这个条件
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait(); //挂起线程等待数据出现
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get %d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
        notifyAll();
    }
}
