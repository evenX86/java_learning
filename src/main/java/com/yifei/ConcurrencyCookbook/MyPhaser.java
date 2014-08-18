package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.Phaser;

/**
 * 并发阶段任务中的阶段切换。
 * onAdvance() 传入当前的阶段数和注册的参与者数量
 * Created by xuyifei01 on 14-8-18.
 */
public class MyPhaser extends Phaser {

    /**
     * 覆盖onAdvance类，在arriveAndAwaitAdvance()方法里休眠的线程被唤醒之前会被自动调用。
     * 返回false表示没有终止，返回true表示终止状态。
     * @param phase
     * @param registeredParties
     * @return
     */
    protected boolean onAdvance(int phase,int registeredParties) {
        switch (phase) {
            case 0:return studentsArrived();
            case 1:return finishFirstExercise();
            case 2:return finishSecondExercise();
            case 3:return finishExam();
            default:return true;
        }

    }

    /**
     * 返回false表示phaser继续在执行中
     * @return false
     */
    private boolean finishExam() {
        System.out.printf("All students finish the exam\n");
        return true;
    }

    private boolean finishSecondExercise() {
        System.out.println("third");
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.println("second");
        return false;
    }

    private boolean studentsArrived() {
        System.out.println("first");
        return false;
    }

}
