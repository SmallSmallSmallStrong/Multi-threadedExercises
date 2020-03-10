package example.lock;

public class DeadLock {
    private LockService service;
    private Object lock = new Object();


    public DeadLock(LockService service) {
        this.service = service;
    }

    public void m1() {
        synchronized (lock) {
            System.out.println("m1");
            service.s1();
        }
    }
    public void m2() {
        synchronized (lock) {
            System.out.println("m2");
        }
    }

}
