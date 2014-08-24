package com.yifei.ConcurrencyCookbook;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by xuyifei01 on 14-8-24.
 */
public class Task2 implements Callable<String>{
    private String name;

    public Task2(String name) {
        this.name = name;
    }

    public String call() {
        System.out.println(name + "start at:" + new Date());
        return "!";
    }
    public static void main(String[] args) {
        //Executors工厂类,创建线程池中只有一个线程
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        System.out.println("Start : " + new Date());
        for (int i=0;i<5;i++) {
            Task2  task2 = new Task2("Task "+ i);
            //即将进行的任务，任务执行前要等待的时间,等待时间的单位
            //这里的延迟的任务多线程是针对上面那个start的
            executor.schedule(task2,i+5, TimeUnit.SECONDS);
        }
        executor.shutdown();
        //等待所有方法结束。
        try {
            //除非所有任务都结束了或者一天。
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ends : "+new Date());
    }
}
