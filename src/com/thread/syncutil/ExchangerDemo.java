package com.thread.syncutil;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("exchanger before, thread:" + name);
                try {
                    Thread.sleep(1000);
                    String x = name;
                    String exchange = exchanger.exchange(x);
                    System.out.println("exchangering, thread:" + name + ", exchange:" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exchanger after, thread:" + name);
            }
        };

        Thread thread1 = new Thread(runnable, "t1");
        Thread thread2 = new Thread(runnable, "t2");
        thread1.start();
        thread2.start();

        System.out.println("start");
    }
}
