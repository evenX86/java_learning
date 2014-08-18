package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xuyifei01 on 14-8-15.
 */
public class Searcher implements Runnable {
    private int firstRow;
    private int lastRow;
    private MatrixMock matrixMock;
    private Results results;
    private int number;
    private final CyclicBarrier cyclicBarrier;

    public Searcher(int firstRow, int lastRow, MatrixMock matrixMock, Results results, int number, CyclicBarrier cyclicBarrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.matrixMock = matrixMock;
        this.results = results;
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            int row[] = matrixMock.getRow(i);
            counter = 0;
            for(int j =0;j<row.length;j++){
                if (row[j] == number) {
                    counter++;
                }
            }
            results.setData(i,counter);
        }
        System.out.printf("%s: Lines processed .\n",Thread.currentThread().getName());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
