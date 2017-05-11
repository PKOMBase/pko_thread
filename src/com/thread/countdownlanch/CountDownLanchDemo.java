package com.thread.countdownlanch;

import java.util.concurrent.CountDownLatch;

public class CountDownLanchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread mainThread = Thread.currentThread();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread:" + Thread.currentThread().getName() + ", mainThreadState:"
                        + mainThread.getName() + " , " + mainThread.getState());
                countDownLatch.countDown();
            }
        };

        Thread thread1 = new Thread(runnable, "t1");
        Thread thread2 = new Thread(runnable, "t2");
        thread1.start();
        thread2.start();
        System.out.println("main start, mainThreadState:" + mainThread.getName() + " , " + mainThread.getState());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }
}
