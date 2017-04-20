package com.thread.volatilekey;

public class Main {

    private static volatile boolean a;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (;;) {
                    if (a == !a) {
                        System.out.println("a == !a");
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (;;) {
                    a = !a;
                }
            }
        });

        thread1.start();
        Thread.sleep(100);
        thread2.start();
    }
}
