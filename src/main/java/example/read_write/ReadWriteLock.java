package example.read_write;

/*
是否进行串行化（加锁）处理[y/n?]
        +-------------------------+
        +      |READ  |   WRITE	  +
        +-------------------------+
        + READ |  N   |     Y     +
        +-------------------------+
        +WRITE |  Y   |     Y     +
        +-------------------------+
*/


public class ReadWriteLock {
    // 正在读取数据的线程数量
    private int readingReaders = 0;//读取者
    // 等待读取数据的线程数量
    private int waitingReaders = 0;//等待读取者
    // 正在写入数据的线程数量
    private int writingWriters = 0;//写入者
    // 等待写入数据的线程数量
    private int waitingWriters = 0;//等待写入者
    //是否偏爱写入
    private boolean prefWrite;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean prefWrite) {
        this.prefWrite = prefWrite;
    }
    //读取方法加锁
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            while (this.writingWriters > 0   || (prefWrite && writingWriters > 0)) {
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnlock() {
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (this.readingReaders > 0 || this.waitingReaders > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnlock() {
        this.writingWriters--;
        this.notifyAll();
    }

}
