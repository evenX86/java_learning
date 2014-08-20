package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xuyifei01 on 14-8-20.
 */
public class Server {
    //Declare a ThreadPoolExecutorattribute named executor
    private ThreadPoolExecutor executor;

    // Implement the constructor of the class that initializes the ThreadPoolExecutor object using the Executorsclass.
    public Server() {
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void ExecuteTask(Task task) {
        System.out.printf("Server: A new task has arrived\n");
        executor.execute(task);
        System.out.println("Server Pool Size : " + executor.getPoolSize());
        System.out.println("Active Count : " + executor.getActiveCount());
        System.out.println("Completed Task : " + executor.getCompletedTaskCount());
    }
    public void endServer() {
        executor.shutdown();
    }
    public static void main(String[] args) {
        Server server = new Server();
        for (int i=0;i<10;i++) {
            Task task = new Task("Täsk "+ i );
            server.ExecuteTask(task);
        }
        server.endServer();
    }
}
