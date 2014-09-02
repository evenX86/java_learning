package com.yifei.ConcurrencyCookbook.collection;

/**
 * Created by xuyifei on 14-9-2.
 */
public class Bank implements Runnable {
    public Bank(Account account) {
        this.account = account;
    }

    private Account account;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.subtracAmount(1000);
        }

    }

    public static void main(String[] args) {
        Account account1 = new Account();
        account1.setBalance(1000);
        Company company = new Company(account1);
        Thread companyThread = new Thread(company);
        Bank bank = new Bank(account1);
        Thread bankThread = new Thread(bank);
        System.out.println("initial banlance :" + account1.getBalance());
        companyThread.start();
        bankThread.start();
        try {
            companyThread.join();
            bankThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final banlance : " + account1.getBalance());
    }
}
