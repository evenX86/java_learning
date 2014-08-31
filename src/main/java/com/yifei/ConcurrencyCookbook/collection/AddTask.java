package com.yifei.ConcurrencyCookbook.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 并发集合
 * Created by xuyifei on 14-8-31.
 */
public class AddTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public AddTask() {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + " :Element " + i);

        }

    }
}
