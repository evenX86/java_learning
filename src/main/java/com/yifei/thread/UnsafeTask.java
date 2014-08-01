package com.yifei.thread;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 开始时间不同但是其结束时间却是相同的。
 * Created by xuyifei01 on 14-8-1.
 */
public class UnsafeTask implements Runnable {
    private Date startDate;
    @Override
    public void run() {
        startDate = new Date();
        System.out.printf("Starting Thread : %s : %s\n",Thread.currentThread().getId(),startDate);
        try{
            TimeUnit.SECONDS.sleep((long) Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished : %s:%s\n", Thread.currentThread().getId(), startDate);
    }
    public static void main(String[] args) {
        UnsafeTask unsafeTask = new UnsafeTask();
        for (int i=0;i<10;i++) {
            Thread thread = new Thread(unsafeTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
