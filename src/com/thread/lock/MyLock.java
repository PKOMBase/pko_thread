package com.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private final Sync sync;

    public MyLock() {
        sync = new Sync();
    }

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }

        @Override
        protected boolean tryAcquire(int arg) {
            Thread currentThread = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                // 获取

                // // 公平锁
                // if (hasQueuedPredecessors()) {
                // return false;
                // }

                compareAndSetState(0, state + arg);
                setExclusiveOwnerThread(currentThread);
                return true;
            }

            if (isHeldExclusively()) {
                setState(state + arg);
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int newState = getState() - arg;
            setState(newState < 0 ? 0 : newState);
            if (newState == 0) {
                setExclusiveOwnerThread(null);
            }
            return true;
        }

        public void lock() {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                acquire(1);
            }
        }
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.new ConditionObject();
    }

}
