package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.*;

/**
 * Created by xuyifei01 on 14-8-25.
 */
public class Task3 implements Callable<String>{
    @Override
    public String call() throws Exception {
        while (true) {
            System.out.println("Task : test\n");
            Thread.sleep(100);
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task3 task3 = new Task3();
        Future<String> result = executor.submit(task3);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result.cancel(true);
        System.out.println("Cancelled :" + result.isCancelled());
        System.out.println("Done : "+result.isDone());
        executor.shutdown();
    }
}
