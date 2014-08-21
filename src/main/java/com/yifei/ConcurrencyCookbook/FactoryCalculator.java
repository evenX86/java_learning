package com.yifei.ConcurrencyCookbook;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by xuyifei01 on 14-8-21.
 */
public class FactoryCalculator implements Callable<Integer> {
    private Integer number;

    public FactoryCalculator(Integer number) {
        this.number = number;
    }


    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (number == 0 || number == 1) {
            return 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.SECONDS.sleep(20);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        List<Future<Integer>> resultList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);
            FactoryCalculator calculator = new FactoryCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }
        System.out.println("getCompletedTaskCount " + executor.getCompletedTaskCount());
        System.out.println("ResultList.size : "+resultList.size());
        while(executor.getCompletedTaskCount()<resultList.size()) {
            System.out.println("Complete : "+executor.getCompletedTaskCount());
            for (int i=0;i<resultList.size();i++) {
                Future<Integer>result  = resultList.get(i);
                System.out.printf("Main : Task %d : %s\n",i,result.isDone());
            }
            try {
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=0;i<resultList.size();i++) {
                Future<Integer> result = resultList.get(i);
                Integer number = null;
                try {
                    number = result.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("Result :"+ number);
            }
        }
        executor.shutdown();

    }
}
