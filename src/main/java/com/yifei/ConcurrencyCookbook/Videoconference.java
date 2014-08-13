package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xuyifei01 on 14-8-13.
 */
public class Videoconference implements Runnable{
    private final CountDownLatch controller;

    public Videoconference(int number) {
        this.controller = new CountDownLatch(number);
    }
    public void arrive(String name) {
        System.out.printf("%s has arrived.\n",name);
        //countDown方法被调用后计数器将减1.当计数器到达0的时候将唤起所有await方法上等待的线程。
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());

    }

    @Override
    public void run() {
        //打印出这次视频会议的人数
        System.out.printf("VideoConference:Initialization:%d participants .\n",controller.getCount());
        try {
            //调用await方法等待所有与会者到达。
            controller.await();
            System.out.printf("VideoConference : All the Participants have come\n");
            System.out.printf("VideoConference : Let's start...\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
