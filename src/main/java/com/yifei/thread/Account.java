package com.yifei.thread;

/**
 * 使用synchronized关键字实现同步方法。
 * 如果一个对象已用syncronized关键字声明，那么只有一个执行线程被允许访问它。
 * 每一个synchronized关键字声明的方法都是临界区。
 *
 * 实现一个银行账户的模型
 * Created by xuyifei01 on 14-8-2.
 */
public class Account {
    private double balance;//余额

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 将传入的数量加入到余额中扣除，并且同一时间只允许一个线程改变这个值。
     * @param amount
     */
    public synchronized void addAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        balance = tmp;
    }

    /**
     * 将传入的数量从余额中扣除
     * @param amount
     */
    public synchronized void subtractAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= amount;
        balance = tmp;
    }
}
