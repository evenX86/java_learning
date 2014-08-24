package com.yifei.ConcurrencyCookbook;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xuyifei01 on 14-8-22.
 */
public class Task1 implements Callable<Result> {
    private String name;

    public Task1(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int value = 0;
        for (int i = 0; i < 5; i++) {
            value += Math.random() * 100;
        }
        Result result = new Result();
        result.setName(this.name);
        result.setValue(value);
        System.out.println(this.name + ": Ends");
        return result;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
        }

    }
}
