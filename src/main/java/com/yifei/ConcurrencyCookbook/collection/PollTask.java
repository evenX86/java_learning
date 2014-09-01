package com.yifei.ConcurrencyCookbook.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 非阻塞式线程安全，使用size()返回值可能不是真实的，在取出size的时候可能有线程正在对list进行修改。
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
            AddTask addTask = new AddTask(concurrentLinkedDeque);
            threads[i] = new Thread(addTask);
            threads[i].start();
        }
        System.out.printf("Main : %d AddTask has been lanched",threads.length);
        for (int i=0;i<threads.length;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the size of list : "+concurrentLinkedDeque.size());
        for (int i=0;i<threads.length;i++) {
            PollTask task = new PollTask(concurrentLinkedDeque);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.println(threads.length + "task threads have been launched");
        for (int i=0;i<threads.length;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
