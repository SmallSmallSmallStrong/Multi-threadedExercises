package example.read_write;

/**
 * 读写锁，可以多线程读，单线程写，写的时候不可读
 */
public class Main {
    public static void main(String[] args) {
        SharedData data = new SharedData(10,false);
        new ReadWorker(data, "R1").start();
        new ReadWorker(data, "R2").start();
        new ReadWorker(data, "R3").start();
        new ReadWorker(data, "R4").start();
        new ReadWorker(data, "R5").start();

        new WriteWorker(data, "W1", "qwertyuiop").start();
        new WriteWorker(data, "W2", "QWERTYUIOP").start();

    }
}
