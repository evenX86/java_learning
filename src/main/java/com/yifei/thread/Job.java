package com.yifei.thread;

/**
 * Created by xuyifei01 on 14-8-3.
 */
public class Job implements Runnable {
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    private  PrintQueue printQueue ;

    @Override
    public void run() {
        System.out.printf("%s: Going to Print a ducument \n",Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The Document has been printed\n",Thread.currentThread().getName());
    }

}
