package example.read_write;

/**
 * 数据缓冲区，共享数据类：临界区
 * <p>
 * 该类中的读、写操作都是线程间遵守读写锁规则的操作，
 * 这样可以实现读操作的并行，写操作串行，提高效率
 */
public class SharedData {
    private final char[] buffer;
    private ReadWriteLock lock;

    public SharedData() {
        this(100, true);
    }

    public SharedData(int size, boolean prefWrite) {
        this.lock = new ReadWriteLock(prefWrite);
        buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '-';
        }
    }

    /**
     * 读取缓冲区buffer中的内容返回给调用者
     *
     * @return 缓冲区的数据
     * @throws InterruptedException 读取加锁异常
     */
    public String read() throws InterruptedException {
        lock.readLock();
        String buf = doRead();
        slowly(10);
        lock.readUnlock();
        return buf;
    }

    private void slowly(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String doRead() {
        char[] newBuf = new char[buffer.length];
        System.arraycopy(buffer, 0, newBuf, 0, buffer.length);
        return String.valueOf(newBuf);
    }

    /**
     * 将c 写入缓冲区 buffer
     *
     * @param c 想要写入的 char
     * @throws InterruptedException 加锁失败
     */
    public void write(char c) throws InterruptedException {
        lock.writeLock();
        doWrite(c);
        slowly(10);
        lock.writeUnlock();
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
        }
    }


    public char[] getBuffer() {
        return buffer;
    }
}
