package com.yifei.thread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei01 on 14-8-1.
 */
public class WriterTask implements Runnable{
    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    private Deque<Event> deque;

    @Override
    public void run() {
        for (int i=1;i<100;i++){
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The Thread %s has generated an event",Thread.currentThread().getId()));
            deque.addFirst(event);
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
class Event{
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}