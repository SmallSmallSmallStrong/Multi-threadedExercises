package example.actionContent;

import java.util.concurrent.Executor;
import java.util.stream.IntStream;

/**
 * 线程保险箱:
 * 所谓的线程保险箱是解决了多线程情况下，各个线程通过 content 高效的串联程序间的数据传输问题；
 *
 * 使用的时候要注意的一个问题就是，一个线程执行完成之后，回归线程池，但是这个时候此线程的Content 对象并没有置空，
 * 需要你手动置空，这样线程再次启动的时候就没啥问题了；
 */
public class Main {
    public static void main(String[] args) {
        IntStream.range(0,5).forEach(n ->{
            new Thread( new ExecuteTask()).start();
        });
    }
}
