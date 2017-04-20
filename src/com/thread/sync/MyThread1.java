package com.thread.sync;

public class MyThread1 implements Runnable {

    private String msg;

    public MyThread1(String msg) {
        super();
        this.msg = msg;
    }

    public void doSomething(String msg) {
        System.out.println("excuted by thread1:" + Thread.currentThread().getName());
        System.out.println(msg);
    }

    public void run() {
        doSomething(this.msg);
    };
}
