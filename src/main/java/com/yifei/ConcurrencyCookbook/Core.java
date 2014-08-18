package com.yifei.ConcurrencyCookbook;

import com.yifei.thread.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 并发任务间的数据交换。
 *
 * Created by xuyifei01 on 14-8-18.
 */
public class Core {
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        Exchanger<List<String>> exchanger = new Exchanger<>();
        Producer producer = new Producer(buffer1,exchanger);
        Consumer consumer = new Consumer(buffer2,exchanger);
        Thread thread = new Thread(producer);

        Thread thread1 = new Thread(consumer);
        thread.start();
        thread1.start();
    }
}
