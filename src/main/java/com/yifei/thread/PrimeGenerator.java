package com.yifei.thread;

/**
 * Created by xuyifei01 on 14-7-31.
 */
public class PrimeGenerator extends Thread {
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n",number);
            }
            if (isInterrupted()){
                System.out.println("The Prime Generator has been Interrupted");
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();
        try {
           Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(task.isInterrupted());
        task.interrupt();
        System.out.println(task.isInterrupted());
    }

}
