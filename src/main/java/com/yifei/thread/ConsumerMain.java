package com.yifei.thread;

/**
 * Created by xuyifei01 on 14-8-3.
 */
public class ConsumerMain {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Thread thread = new Thread(producer);
        Consumer consumer = new Consumer(eventStorage);
        Thread thread1 = new Thread(consumer);
        thread1.start();
        thread.start();
    }
}
