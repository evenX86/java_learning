package com.yifei.thread;

/**
 * 使用syncronized实现同步方法
 * join详解: <a href="http://uule.iteye.com/blog/1101994"></a>
 *
 * Created by xuyifei01 on 14-8-2.
 */
public class Company implements Runnable {
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.addAmount(100);
        }
    }

    public static void main(String[] args) {
        Account account1 = new Account();
        account1.setBalance(1000);
        Company company = new Company(account1);
        Thread companyThread = new Thread(company);
        Bank bank = new Bank(account1);
        Thread bankThread = new Thread(bank);
        System.out.printf("Account: Initial Balance: %f\n",account1.getBalance());
      //  companyThread.start();
      //  bankThread.start();
        try {
            companyThread.join();   //调用companyThread,等到companyThread执行完毕才往下走,下同
            System.out.printf("Account: Final Balance: %f\n",account1.getBalance());

            bankThread.join();
            System.out.printf("Account: Final Balance: %f\n",account1.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
