package com.yifei.ConcurrencyCookbook.customizing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xuyifei on 14-9-3.
 */
public class SleepTwoSecondTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(2, 4, 100, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        List<Future<String>>results = new ArrayList<>();
        for (int i=0;i<10;i++) {
            SleepTwoSecondTask sleepTwoSecondTask = new SleepTwoSecondTask();
            Future<String> result = myExecutor.submit(sleepTwoSecondTask);
            results.add(result);
        }
        for (int i=0;i<5;i++) {
            try {
                String result = results.get(i).get();
                System.out.println("Result for Task "+i+" is "+result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        myExecutor.shutdown();
        for (int i=5;i<10;i++) {
            try {
                String result = results.get(i).get();
                System.out.println("Result for Task "+i+" is "+result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            myExecutor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main : End Of the Problem");
    }
}
