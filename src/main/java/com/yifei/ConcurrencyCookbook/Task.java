package com.yifei.ConcurrencyCookbook;

import java.util.Date;

/**
 * Created by xuyifei01 on 14-8-19.
 */
public class Task implements Runnable {
    private Date initDate;

    public Task(String name, Date initDate) {
        this.name = name;
        this.initDate = initDate;
    }

    private String name;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" AT :"+initDate);
        System.out.println(Thread.currentThread().getName()+" AT :"+new Date());
        try {
            long duration = (long) (Math.random()*10);
            System.out.printf("%s: Doing a task during %d seconds",Thread);
        }
    }
}
