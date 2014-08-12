package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.Semaphore;

/**
 * Created by xuyifei01 on 14-8-12.
 */
public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue() {
        this.semaphore = new Semaphore(2);
    }


    public void printJob(Object document) {
        try {
            //通过调用acquire来获得信号量，必须通过这个方法来获得信号量
            semaphore.acquire();    //Acquires a permit, if one is available and returns immediately
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue:Printing a Job during %d seconds\n",
                    Thread.currentThread().getName(), duration);
            Thread.sleep(duration);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();//释放信号量,必须操作
        }
    }


    public static void main(String[] args) {
        PrintQueue p = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(p), "Thread" + i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

}
