package com.yifei.ConcurrencyCookbook.collection;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * Created by xuyifei on 14-9-2.
 */
public class Task implements Runnable {
    private int id;
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    public Task() {

    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + id * 1000);
        System.out.println(id + " id,delay: " + delay);
        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }

    public static void main(String[] args) {
        DelayQueue<Event> queue1 = new DelayQueue<>();
        Thread threads[] = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i+1,queue1);
            threads[i] = new Thread(task);
        }
        for (int i=0;i<threads.length;i++) {
            threads[i].start();
        }
        for (int i=0;i<threads.length;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        do {
            int counter =0;
            Event event;
            do {
                event = queue1.poll();
                if (event!=null) {
                    counter++;
                }

            } while (event!=null);
        }while (queue1.size()>0);
    }
}
