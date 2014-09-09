package com.yifei.ConcurrencyCookbook.customizing;

import java.util.Date;

/**
 * Created by xuyifei01 on 14-9-9.
 */
public class MyThread extends Thread {
    private Date CreationDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }

    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    public void setCreationDate() {
        this.CreationDate = new Date();
    }

    public void setStartDate() {
        this.startDate = new Date();
    }

    public void setFinishDate() {
        this.finishDate = new Date();
    }

    @Override
    public String toString() {
        return "MyThread{" +
                "CreationDate=" + CreationDate +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }

    public long getExecutionTime() {
        return finishDate.getTime()-startDate.getTime();
    }

}
