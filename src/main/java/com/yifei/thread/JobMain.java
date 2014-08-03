package com.yifei.thread;

/**
 * Created by xuyifei01 on 14-8-3.
 */
public class JobMain {
    public static void main(String[] args) {
        PrintQueue printQueue1 = new PrintQueue();
        Thread thread[] =new Thread[10];
        for (int i=0;i<10;i++) {
            thread[i] = new Thread(new Job(printQueue1),"thread "+i);
        }
        for (int i=0;i<10;i++){
            thread[i].start();
        }
    }
}
