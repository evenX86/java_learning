package com.yifei.ConcurrencyCookbook.customizing;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei on 14-9-3.
 */
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        for (int i = 0; i < 4; i++) {
            MyPriorityTask task = new MyPriorityTask(i, "Task" + i);
            executor.execute(task);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 4; i < 8; i++) {
            MyPriorityTask task = new MyPriorityTask(i, "Task " + i);
            executor.execute(task);
        }
        executor.shutdown();
        //之前使用了executor.shutdownNow（）结果一直报线程interrrupt的exception
        //shutdown() 方法在终止前允许执行以前提交的任务，而 shutdownNow() 方法阻止等待任务启动并试图停止当前正在执行的任务。
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End Of The Program");
    }
}
