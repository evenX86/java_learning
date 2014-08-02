package com.yifei.thread;

import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * 线程分组
 * Created by xuyifei01 on 14-8-2.
 */
public class SearchTask implements Runnable {
    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread : %s:start\n",name);
        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.printf("Thread: %s Interrupted\n",name);
        }
        System.out.printf("Thread : %s:end now:%s\n",name,new Date());
    }

    private void doTask() throws InterruptedException {
        Random random = new Random(new Date().getTime());
        int value = (int) (random.nextDouble()*100);
        System.out.printf("Thread %s: %d,NOW:%s\n",Thread.currentThread().getName(),value,new Date());
        TimeUnit.SECONDS.sleep(value);
    }
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("Searcher");//创建一个标志为Searcher的线程组对象。
        Result result1 = new Result();
        SearchTask searchTask = new SearchTask(result1);
        for(int i=0;i<5;i++) {
            Thread thread = new Thread(threadGroup,searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Number of Threads:%d\n",threadGroup.activeCount());
        System.out.printf("Infomation about the ThreadGroup \n");
        threadGroup.list();
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for(int i=0;i<threadGroup.activeCount();i++) {
            System.out.printf("Thread  %s:%s\n",threads[i].getName(),threads[i].getState());
        }
        waitFinish(threadGroup);
        threadGroup.interrupt();

    }
    private static void waitFinish(ThreadGroup threadGroup) {
        if (threadGroup.activeCount()>9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

class Result {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}