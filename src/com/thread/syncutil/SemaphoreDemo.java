package com.thread.syncutil;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("exchanger before, thread:" + name);
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("exchangering, thread:" + name);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exchanger after, thread:" + name);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable, "t" + i);
            thread.start();
        }

        System.out.println("start");
    }
}
