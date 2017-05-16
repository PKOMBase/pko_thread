package com.thread.exchange;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {

        Exchanger<StringBuffer> exchanger = new Exchanger<StringBuffer>();

        Runnable runnable1 = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                StringBuffer tempStringBuffer = new StringBuffer("12345678");
                System.out.println("exchanger before, thread1:" + name + " , string:" + tempStringBuffer.toString());
                try {
                    Thread.sleep(1000);
                    StringBuffer exchange = exchanger.exchange(tempStringBuffer);
                    System.out.println("exchangering, thread1:" + name + ", old:" + tempStringBuffer.toString()
                            + ", exchange:" + exchange.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exchanger after, thread1:" + name);
            }
        };
        Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                StringBuffer tempStringBuffer = new StringBuffer("ABCD");
                System.out.println("exchanger before, thread2:" + name + " , string:" + tempStringBuffer.toString());
                try {
                    Thread.sleep(1000);
                    StringBuffer exchange = exchanger.exchange(tempStringBuffer);
                    System.out.println("exchangering, thread2:" + name + ", old:" + tempStringBuffer.toString()
                            + ", exchange:" + exchange.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exchanger after, thread2:" + name);
            }
        };
        Runnable runnable3 = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                StringBuffer tempStringBuffer = new StringBuffer("一个");
                System.out.println("exchanger before, thread3:" + name + " , string:" + tempStringBuffer.toString());
                try {
                    Thread.sleep(1000);
                    StringBuffer exchange = exchanger.exchange(tempStringBuffer);
                    System.out.println("exchangering, thread3:" + name + ", old:" + tempStringBuffer.toString()
                            + ", exchange:" + exchange.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exchanger after, thread3:" + name);
            }
        };
        Runnable runnable4 = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                StringBuffer tempStringBuffer = new StringBuffer("一1one");
                System.out.println("exchanger before, thread4:" + name + " , string:" + tempStringBuffer.toString());
                try {
                    Thread.sleep(1000);
                    StringBuffer exchange = exchanger.exchange(tempStringBuffer);
                    System.out.println("exchangering, thread4:" + name + ", old:" + tempStringBuffer.toString()
                            + ", exchange:" + exchange.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exchanger after, thread4:" + name);
            }
        };

        Thread thread1 = new Thread(runnable1, "t1");
        Thread thread2 = new Thread(runnable2, "t2");
        Thread thread3 = new Thread(runnable3, "t3");
        Thread thread4 = new Thread(runnable4, "t4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println("start");
    }
}
