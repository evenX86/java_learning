package com.yifei.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 使用工厂类创建线程，工厂模式是一个创建者模式，使用一个类为其他他的一个或者多个类创建对象，当我们要为这些类
 * 创建对象时不需要再使用new构造器而使用工厂类。使用工厂类可以使对象的创建集中化
 *
 * Created by xuyifei01 on 14-8-2.
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String name;
    private List<String>stats;

    public MyThreadFactory(String name) {
        this.name = name;
        counter = 0;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r,name+"-Thread_"+counter);
        counter ++;
        stats.add(String.format("Created thread %d with name %s on %s \n",t.getId(),t.getName(),new Date()));
        return t;
    }
    public String getStats() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> iterator = stats.iterator();
        while (iterator.hasNext()) {
            stringBuffer.append(iterator.next());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }
    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
        Task task = new Task();
        Thread thread;
        System.out.println("Starting The Thread");
        for(int i=0;i<10;i++) {
            thread = myThreadFactory.newThread(task);
            thread.start();
        }
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n",myThreadFactory.getStats());
    }
}
class Task implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
