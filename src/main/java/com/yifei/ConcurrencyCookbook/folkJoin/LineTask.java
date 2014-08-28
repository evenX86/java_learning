package com.yifei.ConcurrencyCookbook.folkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 用来计算所要查找的次在一行中出现的次数。
 * Created by xuyifei01 on 14-8-28.
 */
public class LineTask extends RecursiveTask<Integer> {
    private String[] line;
    private int start, end;
    private String word;

    public LineTask(String[] strings, int i, int length, String word) {
        this.line = strings;
        this.start = i;
        this.end = length;
        this.word = word;

    }

    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 100) {
            result = count(line, start, end, word);
        } else {
            int mid = (start + end) / 2;
            LineTask task1 = new LineTask(line, start, mid, word);
            LineTask task2 = new LineTask(line, mid, end, word);
            invokeAll(task1, task2);
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    private Integer groupResults(Integer integer, Integer integer1) {
        return integer + integer1;
    }

    private Integer count(String[] line, int start, int end, String word) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (word.equals(line[i])) {
                count++;
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }
    public static void main(String[] args) {
        Document document = new Document();
        String[][] doc = document.generateDocument(100,1000,"the");
        DocumentTask task = new DocumentTask(doc,0,100,"the");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        while (!task.isDone()) {
            System.out.println("**************************************");
            System.out.println("getParallelism "+pool.getParallelism());
            System.out.println("getQueuedTaskCount "+pool.getQueuedTaskCount());//返回由工作线程队列中目前持有的总任务数的估计值（但不包括向那些尚未开始执行池任务）。
            System.out.println("getStealCount "+pool.getStealCount());
            System.out.println("**************************************");
        }
        pool.shutdown();
        try {
            pool.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("words appeared : "+task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
