package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuyifei01 on 14-8-12.
 */
public class PrintQueue {
    private final Semaphore semaphore;
    private boolean freePrinters[];
    private Lock lockPrinters;

    public PrintQueue() {
        freePrinters = new boolean[3];
        this.semaphore = new Semaphore(3);
        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    private int getPrinter() {
        int ret = -1;
        try {
            lockPrinters.lock();
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPrinters.unlock();
        }
        return ret;
    }

    public void printJob(Object document) {
        try {
            //通过调用acquire来获得信号量，必须通过这个方法来获得信号量
            semaphore.acquire();    //Acquires a permit, if one is available and returns immediately
            int assignedPrinter = getPrinter();
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue:Printing a Job in Printer %d during %d seconds\n",
                    Thread.currentThread().getName(), assignedPrinter,duration);
            Thread.sleep(duration);
            freePrinters[assignedPrinter]=true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();//释放信号量,必须操作
        }
    }


    public static void main(String[] args) {
        PrintQueue p = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(p), "Thread" + i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

}
