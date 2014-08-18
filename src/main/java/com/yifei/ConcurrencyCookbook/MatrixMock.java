package com.yifei.ConcurrencyCookbook;

import java.util.Random;

/**
 * CyclicBarrier 类练习。
 * Created by xuyifei01 on 14-8-13.
 */
public class MatrixMock {
    private int data[][];

    public MatrixMock(int size, int length, int number) {
        this.data = new int[size][length];
        int counter = 0;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt();
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }
        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n",counter);
    }

    /**
     * 返回行数据。
     * @param row
     * @return
     */
    public int[] getRow(int row) {
        if (row>0 && row<data.length){
            return data[row];
        }
        return  null;
    }

}
