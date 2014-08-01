package com.yifei.thread;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 * Created by xuyifei01 on 14-8-1.
 */
public class CleanTask extends Thread {
    public CleanTask(Deque<Event> deque) {
        this.deque = deque;
        setDaemon(true);
    }

    private Deque<Event> deque;

    public void run() {
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        if (deque.size() == 0) {
            return;
        }
        delete = false;
        do {
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if (difference > 10000) {
                System.out.printf("Cleaner: %s\n", e.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while (difference > 10000);
        if (delete) {
            System.out.printf("Cleaner: Size of the queue : %d\n",deque.size());
        }
    }
    public static void main(String[] args) {
        Deque<Event> deque1 = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque1);
        for(int i=0;i<3;i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }
        CleanTask cleanTask = new CleanTask(deque1);
        cleanTask.start();

    }
}
