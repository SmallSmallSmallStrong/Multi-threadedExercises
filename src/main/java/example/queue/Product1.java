package example.queue;

/**
 * 生产者1
 */
public class Product1 {
    private int i = 0;
    private final Object lock = new Object();
    private volatile boolean isProdected = false;

    public void product(String n) {
        synchronized (lock) {
            while (isProdected) {//这里不能使用 if 为啥？？？
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            //这里写产出代码
            System.out.println(n + " -> " + i);
            lock.notifyAll();
            isProdected = true;
        }
    }

    public void consume(String n) {
        synchronized (lock) {
            while (!isProdected) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //这里写消费代码
            System.out.println(n + " -> " + i);
            lock.notifyAll();
            isProdected = false;
        }
    }

}
