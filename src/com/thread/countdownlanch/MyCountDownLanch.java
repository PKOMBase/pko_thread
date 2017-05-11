package com.thread.countdownlanch;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyCountDownLanch {

    private final Sync sync;

    public MyCountDownLanch(int count) {
        sync = new Sync(count);
    }

    static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 0 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;) {
                int state = getState();
                if (state == 0) {
                    return false;
                }
                int newState = state - 1;
                if (compareAndSetState(state, newState)) {
                    return newState == 0;
                }
            }
        }
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public void countDown() {
        sync.releaseShared(1);
    }

}
