package com.thread.sync;

public class MyThread2 extends Thread {

    private String msg;

    public MyThread2(String msg) {
        super();
        this.msg = msg;
    }

    public void doSomething(String msg) {
        System.out.println("excuted by thread2:" + Thread.currentThread().getName());
        System.out.println(msg);
    }

    public void run() {
        doSomething(this.msg);
    };
}
