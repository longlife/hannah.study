package com.hannah.study.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ShareLock {

	private static final class Sync extends AbstractQueuedSynchronizer {
		private static final long serialVersionUID = 7997811808704934604L;

		Sync(int count) {
			setState(count);
		}

		int getCount() {
			return getState();
		}

		@Override
		protected int tryAcquireShared(int acquires) {
			for (;;) {
				int c = getState();
				if (c < acquires)
					return c - acquires;
				int nextc = c - acquires;
				if (compareAndSetState(c, nextc)) {
					System.out.println(Thread.currentThread().getName() + "   lock: " + nextc);
					return nextc;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int releases) {
			for (;;) {
				int c = getState();
				int nextc = c + releases;
				if (compareAndSetState(c, nextc)) {
					System.out.println(Thread.currentThread().getName() + " unlock: " + nextc);
					return true;
				}
			}
		}
	}

	private final Sync sync;

	public ShareLock(int count) {
		if (count < 0)
			throw new IllegalArgumentException("count < 0");
		this.sync = new Sync(count);
	}

	public void lock(int s) {
		sync.acquireShared(s);
	}
	
	public boolean tryLock(int s, long millisTimeout) throws InterruptedException {
		return sync.tryAcquireSharedNanos(s, millisTimeout * 1000000L);
	}

	public void unlock(int s) {
        sync.releaseShared(s);
    }

    public long getCount() {
        return sync.getCount();
    }

    public static void main(String[] args) throws InterruptedException {
    	ShareLock latch = new ShareLock(10);
    	
    	for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					int s = 4;
					try {
						if (latch.tryLock(s, 3900L)) {
							Thread.sleep(1000L);
							latch.unlock(s);
							System.out.println(Thread.currentThread().getName() + " finish >>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
						}
						else {
							System.out.println(Thread.currentThread().getName() + " lock failed .....");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "thread-" + i);
			t.start();
		}
	}
}
