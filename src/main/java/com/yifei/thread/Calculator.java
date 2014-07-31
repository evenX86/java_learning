package com.yifei.thread;


/**
 * Created by xuyifei01 on 14-7-30.
 */
public class Calculator implements Runnable {
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for(int i=1;i<=10;i++) {
         //   System.out.printf("%s : %d * %d = %d\n", Thread.currentThread().getName(),number,i,i*number);
            System.out.println(Thread.currentThread().getName()+" : "+Thread.currentThread().getState()+"Priority : "+Thread.currentThread().getPriority());
        }
    }
}
