package com.thread.threadlocal;

public class ThreadLoaclDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("asadf");
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadLocal after, thread:" + name + " , threadLocal:" + threadLocal.get());
            }
        };
        Thread.sleep(1000);
        Thread thread1 = new Thread(runnable, "t1");
        thread1.start();
        Thread.sleep(1200);
        System.out.println("end, thread:" + Thread.currentThread().getName() + " , threadLocal:" + threadLocal.get());
    }
}
