package com.yifei.ConcurrencyCookbook.customizing;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by xuyifei on 14-9-3.
 */
public class MyExecutor extends ThreadPoolExecutor {
    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                      BlockingQueue<Runnable> workqueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workqueue);
        this.startTime = new ConcurrentHashMap<>();
    }

    private ConcurrentHashMap<String, Date> startTime;

    /**
     * 关闭执行器
     */
    public void shutdown() {
        System.out.println("Going to shutdown the executor");
        System.out.println("Completed : " + this.getCompletedTaskCount());
        System.out.println("Active : " + this.getActiveCount());
        System.out.println("Pending : " + this.getQueue().size());
        super.shutdown();
    }

    public java.util.List<Runnable> shutdownNow() {
        System.out.println("Going to shutdown the executor");
        System.out.println("Completed : " + this.getCompletedTaskCount());
        System.out.println("Active : " + this.getActiveCount());
        System.out.println("Pending : " + this.getQueue().size());
        return super.shutdownNow();
    }

    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("A task is Beginning :" + t.getName()+ " : " + r.hashCode());
        startTime.put(String.valueOf(r.hashCode()), new Date());
    }

    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> result = (Future<?>) r;
        try {
            System.out.println("***********************************");
            System.out.println("A task has finished: "+r.hashCode());
            System.out.println("Result :" + result.get());
            Date startDate = startTime.remove(String.valueOf(r.hashCode()));
            Date finishDate  = new Date();
            long diff = finishDate.getTime() - startDate.getTime();
            System.out.println("duraing time :" + diff);
            System.out.println("************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
