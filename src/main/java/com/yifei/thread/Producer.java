package com.yifei.thread;

/**
 * 生产者类
 * Created by xuyifei01 on 14-8-3.
 */
public class Producer implements Runnable {
    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            eventStorage.set();
        }
    }
}
