package com.yifei.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei01 on 14-8-1.
 */
public class DataSourceLoader implements Runnable {


    @Override
    public void run() {
        System.out.printf("Beginning data sources loading : %s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data Source loading has finished: %s\n",new Date());
    }
    public static void main(String[] args) {
        DataSourceLoader dataSourceLoader =new DataSourceLoader();
        Thread thread1 = new Thread(dataSourceLoader,"DataSourceThread");
        Thread thread2 = new Thread(dataSourceLoader,"DataSourceThread");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
