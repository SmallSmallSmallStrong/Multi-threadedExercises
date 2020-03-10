package example.lock;

public class Main {
    public static void main(String[] args) {
        //以下方法生成了一个死锁
        //分别构建对象 LockService  DeadLock
        LockService service = new LockService();
        DeadLock deadLock = new DeadLock(service);
        service.setDeadLock(deadLock);
        //并且2个对象互为属性
        //启动线程 一直（一直很关键，如果调用一次不会出问题）调用 LockService 的s2方法，s2中调用了 DeadLock 的m2方法
        new Thread(() -> {
            while (true)
                service.s2();
        }).start();
        //启动线程 调用 DeadLock 的m1方法，m1中调用了 LockService 的s1方法
        new Thread(() -> {
            while (true)
                deadLock.m1();
        }).start();
        //如此这般就会造成2个对象的死锁，
        //举一反三，通过这个就可以总结出来3个对象的死锁或者多个对象的死锁
        //当然解决死锁问题最重要的是需要有好的工具， jstack jvisualvm
    }
}
