package example.interrupt;

/**
 * 这里设计了一个能够在指定时间内关闭的线程，
 * 杜绝了某些线程无法关闭的尴尬
 * //TODO 这里可以作为一个工具类使用
 */
public class ThreadService {

    private Thread executeThread;
    private boolean flag = false;
    public void execute(Runnable task) {
        executeThread = new Thread(() -> {
            Thread runner = new Thread(task);
            runner.setDaemon(true);
            runner.start();
            try {
                runner.join();
                //如果执行结束之后修改标志位
                flag = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("任务被强行制止");
            }
        });
        executeThread.start();
    }

    public void shutdown(int mills) {
        long currectTime = System.currentTimeMillis();
        while (!flag) {
            if (System.currentTimeMillis() - currectTime >= mills) {
                System.out.println("任务超时");
                executeThread.interrupt();//打断主任务，如果守护线程，上面的 runner 没有执行完则会因为主线程结束了而结束
                break;
            }
            try {
                executeThread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("执行线程被打断");
                e.printStackTrace();
                break;
            }
        }
        flag = false;
    }

}
