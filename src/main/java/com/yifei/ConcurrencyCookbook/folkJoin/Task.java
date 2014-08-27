package com.yifei.ConcurrencyCookbook.folkJoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuyifei01 on 14-8-27.
 */
public class Task extends RecursiveAction {
    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private int first, last;
    private double increment;

    @Override
    protected void compute() {
        if (last - first > 10) {
            updatePrice();
        } else {
            int middle = (first + last) / 2;
            System.out.printf("pending task : %s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);
        }

    }

    private void updatePrice() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice()*(1+increment));
        }
    }
    public static void main(String[] args) {
        ProductListGenerator genertor = new ProductListGenerator();
        List<Product>products = genertor.generate(100);
        Task task = new Task(products,0,products.size(),0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        while (!task.isDone()) {
            System.out.println("Thread Count : " + pool.getActiveThreadCount());
            System.out.println("Thread Steal : " + pool.getStealCount());
            System.out.println("Parallelism : "+ pool.getParallelism());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        if (task.isCompletedNormally()) {
            System.out.println("The Process has complete");
        }
        for(int i=0;i<products.size();i++) {
            Product p = products.get(i);
            if (p.getPrice()!=12) {
                System.out.printf("Product : %s: %f\n",p.getName(),p.getPrice());
            }
        }
    }
}
