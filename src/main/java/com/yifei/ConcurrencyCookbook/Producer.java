package com.yifei.ConcurrencyCookbook;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by xuyifei01 on 14-8-18.
 */
public class Producer implements Runnable {

    private List<String> buffer;//进行交换的数据结构


    private final Exchanger<List<String>> exchanger; //同步交换对象

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Producer :Cycle:%d\n", cycle);
            //在每个循环中添加是个字符串到buffer列表中
            for (int j = 0; j < 10; j++) {
                String message = "Event " + (i * 10 + j);
                System.out.printf("Producer :%s\n", message);
                buffer.add(message);
            }
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer : " + buffer.size());
            cycle++;
        }
    }
}
