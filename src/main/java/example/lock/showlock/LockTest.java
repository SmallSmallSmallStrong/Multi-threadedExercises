package example.lock.showlock;

import com.jayway.jsonpath.Option;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) {
        BooleanLock booleanLock = new BooleanLock();
        Stream.of("t1", "t2", "t3", "t4", "t5").forEach(name -> {
            Thread t = new Thread(() -> {
                try {
                    booleanLock.lock(50);
//                    booleanLock.lock();
                    Optional.of(Thread.currentThread().getName() + " getlock ").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Lock.TimeOutException e) {
                    System.out.println(Thread.currentThread().getName() + " 工作超时");
//                    e.printStackTrace();
                } finally {
                    booleanLock.unlock();
                    Optional.of(Thread.currentThread().getName() + " unlock").ifPresent(System.out::println);
                }
            }, name);
            t.setUncaughtExceptionHandler((thread, e) -> {
                System.out.println(thread.getName());
                System.out.println(e);
            });
            t.start();

        });
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " work Start").ifPresent(System.out::println);
        Thread.sleep(10_000);
        Optional.of(Thread.currentThread().getName() + " work END").ifPresent(System.out::println);
    }
}
