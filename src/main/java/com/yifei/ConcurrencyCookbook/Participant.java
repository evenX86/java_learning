package com.yifei.ConcurrencyCookbook;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 与会者类
 * 学习CountDownLatch类
 * Created by xuyifei01 on 14-8-13.
 */
public class Participant implements Runnable {

    private Videoconference videoconference;
    private String name;

    public Participant(Videoconference videoconference, String name) {
        this.videoconference = videoconference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);
        //将线程休眠一段时间。
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //arrive 表明一个与会者的到来。
        videoconference.arrive(name);
    }

    public static void main(String[] args) {
        Videoconference conference = new Videoconference(10);
        //将会议对象作为参数传入线程
        Thread thread = new Thread(conference);
        thread.start();
        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(conference,"Participant"+i);
            Thread t = new Thread(participant);
            t.start();
        }

    }
}
