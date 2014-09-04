package com.yifei.ConcurrencyCookbook.collection;

/**
 * Created by xuyifei on 14-9-2.
 */
public class Company implements Runnable {
    public Company(Account account) {
        this.account = account;
    }

    private Account account;
    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            account.addAmount(1000);
        }
    }
}
