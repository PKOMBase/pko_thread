package com.thread.syncutil;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);

        int register = phaser.register();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    System.out.println("register, thread:" + name + ", register:" + register);
                    System.out.println("running start, thread:" + name + ", phase:" + phaser.getPhase());
                    Thread.sleep(100 + (int) (Math.random() * 5000));
                    System.out.println("running end, thread:" + name + ", phase:" + phaser.getPhase());
                    int arriveAndAwaitAdvance = phaser.arriveAndAwaitAdvance();
                    System.out.println("arriveAndAwaitAdvance, thread:" + name + ", arriveAndAwaitAdvance:"
                            + arriveAndAwaitAdvance);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("phaser after, thread:" + name);
            }
        };

        Thread thread1 = new Thread(runnable, "t1");
        Thread thread2 = new Thread(runnable, "t2");
        thread1.start();
        thread2.start();
        // int arriveAndDeregister = phaser.arriveAndDeregister();
        // System.out.println("arriveAndDeregister, thread:" +
        // Thread.currentThread().getName() + ", arriveAndDeregister:"
        // + arriveAndDeregister);
        System.out.println("start");
    }
}
