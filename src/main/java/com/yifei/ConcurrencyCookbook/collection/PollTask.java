package com.yifei.ConcurrencyCookbook.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by xuyifei on 14-8-31.
 */
public class PollTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];
        for (int i=0;i<threads.length;i++) {
            AddTask addTask = new AddTask();
            threads[i] = new Thread(addTask);
            threads[i].start();
        }
        System.out.printf("Main : %d AddTask has been lanched",threads.length);
    }
}
