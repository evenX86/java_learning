package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.CyclicBarrier;

/**
 * 计算在矩阵中找到的总次数
 * Created by xuyifei01 on 14-8-15.
 */
public class Grouper implements Runnable {
    private Results results;


    public Grouper(Results results) {
        this.results = results;
    }

    @Override

    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] =  results.getData();
        for(int number:data) {
            finalResult += number;
        }
        System.out.printf("Grouper Total result:%d.\n",finalResult);
    }
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBER = 1000;
        final int SEARCH = 5;
        final int PARTCIPANTS =5;
        final int LINES_PARTICIPANT = 2000;
        MatrixMock mock = new MatrixMock(ROWS,NUMBER,SEARCH);
        Results results1 = new Results(ROWS);
        Grouper grouper = new Grouper(results1);
        CyclicBarrier barrier = new CyclicBarrier(PARTCIPANTS,grouper);
        Searcher searcher[] = new Searcher[PARTCIPANTS];
        for (int i=0;i<PARTCIPANTS;i++) {
            searcher[i] = new Searcher(i*LINES_PARTICIPANT,i*LINES_PARTICIPANT+LINES_PARTICIPANT,mock,results1,5,barrier);
            Thread thread = new Thread(searcher[i]);
            thread.start();
        }
        System.out.printf("Main : The main Thread has finished.\n");
    }
}
