package com.yifei.ConcurrencyCookbook.collection;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xuyifei on 14-9-2.
 */
public class Account {
    private AtomicLong balance;

    public Account() {
        balance = new AtomicLong();
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
    }
    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
    }
    public void subtracAmount(long amount) {
        this.balance.getAndAdd(-amount);
    }

}
