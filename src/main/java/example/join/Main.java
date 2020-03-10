package example.join;

import java.util.Optional;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            IntStream.range(0, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-1-" + i));
        });
//        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread t = Thread.currentThread();
//                Thread.currentThread()
                IntStream.range(0, 1000).forEach(i -> System.out.println(t.isInterrupted()));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        });
        t2.start();
//        try {
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread main = Thread.currentThread();
//        main.interrupt();
        //线程打断
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        IntStream.range(0, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-3-" + i));


        Optional.of("All of tasks finish done. ").ifPresent(System.out::println);
    }
}
