package example.queue;

import java.util.stream.Stream;

public class MainQueue {
    public static void main(String[] args) {
        main1();
    }

    public static void main1() {
        Product1 p = new Product1();
        Stream.of("P1", "P2").forEach(n -> {
            new Thread(() -> {
                while (true)
                    p.product(n);
            }).start();
        });
        Stream.of("C1", "C2").forEach(n -> {
            new Thread(() -> {
                while (true)
                    p.consume(n);
            }).start();
        });
        /**
         * 上面的状态会造成假死状态，假死状态跟死锁状态很像都是不会再继续执行，这个时候就需要借助工具去看
         * @see example.lock.Main
         */
    }
}
