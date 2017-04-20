package com.thread.state;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {

        Thread main = Thread.currentThread();

        Object object = new Object();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                Thread currentThread = Thread.currentThread();
                System.out.println("start thread1");
                System.out.println("step2 thread1:" + currentThread.getState() + ", main:" + main.getState());

                System.out.println("thread1 get the lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("step3 thread1:" + currentThread.getState() + ", main:" + main.getState());

                System.out.println("end thread1");
                countDownLatch.countDown();
            }
        });

        System.out.println("step1 thread1:" + thread1.getState() + ", main:" + main.getState());

        thread1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
        System.out.println("step4 thread1:" + thread1.getState() + ", main:" + main.getState());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("step4 thread1:" + thread1.getState() + ", main:" + main.getState());

    }
}
