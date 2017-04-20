package com.thread.sync;

public class Main {

    public static void main(String[] args) {
        System.out.println("excuted by main:" + Thread.currentThread().getName());
        MyThread1 myThread1 = new MyThread1("hello1!");
        myThread1.run();
        //
        // Thread myThread1New = new Thread(myThread1, "t1");
        // myThread1New.start();

        MyThread2 myThread2 = new MyThread2("hello2!");
        myThread2.setName("t2");
        myThread2.start();
    }

}
