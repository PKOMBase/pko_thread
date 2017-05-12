package com.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {

            @Override
            public void run() {
                System.out.println("cyclicBarrier end1, thread:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("cyclicBarrier end2");
            }
        });

        Runnable runnable1 = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("await before, thread:" + name);
                try {
                    Thread.sleep(1000);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("await after, thread:" + name);
            }
        };
        Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("await before, thread:" + name);
                try {
                    Thread.sleep(2100 + (int) (Math.random() * 1000));
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("await after, thread:" + name);
            }
        };

        Thread thread1 = new Thread(runnable1, "t1");
        Thread thread2 = new Thread(runnable2, "t2");
        thread1.start();
        thread2.start();

        Thread.sleep(2100);

        System.out.println("thread1:" + thread1.getName() + " , " + thread1.getState());
        System.out.println("thread2:" + thread2.getName() + " , " + thread2.getState());

        System.out.println("end");
    }
}
