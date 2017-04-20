package com.thread.safe;


public class Main {
    private static int result = 0;

    private static String resultString = "";

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Main.class) {
                    resultString += " , " + Thread.currentThread().getName();
                    result++;
                }
            }
        };

        Thread thread;
        for (int i = 0; i < 100; i++) {
            thread = new Thread(runnable, "t" + i);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("result:" + result);
        System.out.println("resultString:" + resultString);
    }
}
