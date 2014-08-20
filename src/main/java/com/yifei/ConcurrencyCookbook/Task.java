package com.yifei.ConcurrencyCookbook;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei01 on 14-8-19.
 */
public class Task implements Runnable {
    private Date initDate;

    public Task(String name) {
        this.name = name;
        this.initDate = new Date();
    }

    private String name;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" AT :"+initDate);
        System.out.println(Thread.currentThread().getName()+" AT :"+new Date());
        try {
            long duration = (long) (Math.random()*10);
            System.out.printf("%s: Task %s :Doing a task during %d seconds\n",Thread.currentThread().getName(),name,duration);
            TimeUnit.SECONDS.sleep(duration);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Task %s: Finished on: %s\n",Thread.
                currentThread().getName(),name,new Date());
    }
}
