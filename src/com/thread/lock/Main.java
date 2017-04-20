package com.thread.lock;

import java.util.concurrent.CountDownLatch;

public class Main {

    private final static MyLock lock = new MyLock();

    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        int count = 2000;

        CountDownLatch countDownLatch = new CountDownLatch(count);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("==start==" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num++;
                    System.out.println("==end==");
                    countDownLatch.countDown();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread thread = null;
        for (int i = 0; i < count; i++) {
            thread = new Thread(runnable, "t" + i);
            thread.start();
        }

        countDownLatch.await();

        System.out.println("==num==" + num);
    }
}
