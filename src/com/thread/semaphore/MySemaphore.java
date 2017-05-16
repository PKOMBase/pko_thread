package com.thread.semaphore;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MySemaphore {

    private Sync sync;

    static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        Sync(int permits) {
            setState(permits);
        }

        protected final boolean tryReleaseShared(int releases) {
            for (;;) {
                int current = getState();
                int next = current + releases;
                if (next < current) // overflow
                    throw new Error("Maximum permit count exceeded");
                if (compareAndSetState(current, next))
                    return true;
            }
        }
    }

    static class NonFairSync extends Sync {

        NonFairSync(int permits) {
            super(permits);
        }

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        protected final int tryAcquireShared(int acquires) {
            for (;;) {
                int available = getState();
                int remaining = available - acquires;
                if (remaining < 0 || compareAndSetState(available, remaining))
                    return remaining;
            }
        }

    }

    static class FairSync extends Sync {

        FairSync(int permits) {
            super(permits);
        }

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        protected int tryAcquireShared(int acquires) {
            for (;;) {
                if (hasQueuedPredecessors())
                    return -1;
                int available = getState();
                int remaining = available - acquires;
                if (remaining < 0 || compareAndSetState(available, remaining))
                    return remaining;
            }
        }
    }

    public MySemaphore(int permits) {
        super();
        this.sync = new NonFairSync(permits);
    }

    public MySemaphore(int permits, boolean isFair) {
        super();
        this.sync = isFair ? new FairSync(permits) : new NonFairSync(permits);
    }

    public void acquire() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public void release() {
        sync.releaseShared(1);
    }

}
