package com.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("run thread:" + Thread.currentThread().getName());
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 50, 3000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(runnable);
        }
    }
}
