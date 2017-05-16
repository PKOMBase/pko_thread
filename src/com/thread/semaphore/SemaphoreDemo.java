package com.thread.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1, true);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("exchanger before, thread:" + name);
                try {
                    semaphore.acquire();
                    Thread.sleep(1200);
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
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("start");
    }

}
