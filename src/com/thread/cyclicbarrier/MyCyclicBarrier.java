package com.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyCyclicBarrier {

    private int parties;

    private int count;

    private Runnable barrierAction;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public MyCyclicBarrier(int parties, Runnable barrierAction) {
        if (parties <= 0)
            throw new IllegalArgumentException();
        this.parties = parties;
        this.count = parties;
        this.barrierAction = barrierAction;
    }

    public int await() throws InterruptedException, BrokenBarrierException {
        lock.lock();
        try {
            // count--
            int index = --count;

            // 如果index为0，运行barrierAction后，唤醒线程
            if (index == 0) {
                if (this.barrierAction != null) {
                    this.barrierAction.run();
                }
                condition.signalAll();

                count = parties;
                return 0;
            }

            // index不为0，线程wait，自旋保证执行结果
            for (;;) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return index;
            }

        } finally {
            lock.unlock();
        }
    }

}
