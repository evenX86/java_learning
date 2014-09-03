package com.yifei.ConcurrencyCookbook.customizing;

import java.util.concurrent.TimeUnit;

/**
 * 使用优先级队列实现基于优先级的Executor类
 * Created by xuyifei on 14-9-3.
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {
    public MyPriorityTask(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    private int priority;
    private String name;

    public int getPriority() {
        return priority;
    }

    @Override
    public void run() {
        System.out.println(name + " priority is " + priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        if (this.getPriority() < o.getPriority()) {
            return 1;
        }
        if (this.getPriority() > o.getPriority()) {
            return -1;
        }
        return 0;
    }

}
