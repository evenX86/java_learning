package com.yifei.ConcurrencyCookbook;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei01 on 14-8-18.
 */
public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("%s has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s : is going to do the first exercise.%s\n", Thread.currentThread().getName(), new Date());
        doExercise1();
        System.out.printf("%s : has done the first exercise.%s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s : is going to do the second exercise.%s\n", Thread.currentThread().getName(), new Date());
        doExercise2();
        System.out.printf("%s : has done the second exercise.%s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s : is going to do the third exercise.%s\n", Thread.currentThread().getName(), new Date());
        doExercise3();
        System.out.printf("%s : has done the third exercise.%s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

    }

    private void doExercise3() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExercise2() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doExercise1() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();
        Student student[] = new Student[3];
        for (int i = 0; i < student.length; i++) {
            student[i] = new Student(myPhaser);
            myPhaser.register();
        }
        Thread thread[] = new Thread[student.length];
        for(int i=0;i<student.length;i++) {
            thread[i] = new Thread(student[i],"Student"+i);
            thread[i].start();
        }
        for(int i=0;i<thread.length;i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: The Phaser has finished :%s\n",myPhaser.isTerminated());
    }
}
