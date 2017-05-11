package com.thread.countdownlanch;


public class CountDownLanchDemo {
    public static void main(String[] args) throws InterruptedException {

        MyCountDownLanch countDownLatch = new MyCountDownLanch(2);

        Thread mainThread = Thread.currentThread();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000 + (int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread" + Thread.currentThread().getName() + ", mainThread:" + mainThread.getName()
                        + " , " + mainThread.getState());
                countDownLatch.countDown();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        countDownLatch.await();

        System.out.println("main end");
    }
}
