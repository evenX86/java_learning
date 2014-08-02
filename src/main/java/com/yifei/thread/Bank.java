package com.yifei.thread;

/**
 * Created by xuyifei01 on 14-8-2.
 */
public class Bank implements Runnable{
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++) {
            account.subtractAmount(100);
        }

    }
}
