package com.yifei.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei01 on 14-8-1.
 */
public class FileClock implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try{
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            } catch (InterruptedException e) {
                System.out.println("The FileClock has been interrupted");
            }
        }
    }
    public static void main(String[] args) {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
