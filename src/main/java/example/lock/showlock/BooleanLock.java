package example.lock.showlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements Lock {
    //是否有线程持有这个锁
    private Boolean intiValue;
    //等待获取锁的线程
    private Collection<Thread> blockedThreadCollection = new ArrayList<>();
    private Thread currentThread;

    public BooleanLock() {
        this.intiValue = Boolean.FALSE;
    }

    public BooleanLock(Boolean intiValue) {
        this.intiValue = intiValue;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (intiValue) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        //记录获取锁的线程
        this.currentThread = Thread.currentThread();
        blockedThreadCollection.remove(Thread.currentThread());
        intiValue = true;
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0)
            lock();
        long has = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (intiValue) {
            long time = System.currentTimeMillis();
            if (has <= 0) {
                throw new TimeOutException("   任务超时");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            has = System.currentTimeMillis() - endTime;
//            System.out.println("耗时：" + (System.currentTimeMillis() - time));
        }
        this.intiValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.intiValue = false;
            System.out.println(Thread.currentThread().getName() + " 以释放lock");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
