package com.yifei.ConcurrencyCookbook;

/**
 * 包含矩阵中每行找到指定数字的次数
 * Created by xuyifei01 on 14-8-15.
 */
public class Results {
    private int data[];
    public Results(int size) {
        data = new int[size];
    }
    public void setData(int position,int value) {
        data[position] = value;

    }
    public int[] getData() {
        return data;
    }
}
