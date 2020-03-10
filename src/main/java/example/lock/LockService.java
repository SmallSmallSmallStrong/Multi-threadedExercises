package example.lock;

public class LockService {

    private DeadLock deadLock;
    private Object lock = new Object();

    public void s1() {
        synchronized (lock) {
            System.out.println("s1");
        }
    }

    public void s2() {
        synchronized (lock) {
            System.out.println("s2");
            deadLock.m2();
        }
    }

    public DeadLock getDeadLock() {
        return deadLock;
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
