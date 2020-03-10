package example.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool {
    private final static int MAX_SIZE = 10;//线程池的最大值
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;//任务池承载的最大任务数量
    private final static String THREAD_PREFIX = "SIMPLE_POOL-";//线程的前缀
    private volatile static int seq;//线程的序号（非线程安全）
    private int DEFALUT_SIZE;//池的默认大小
    private int TASK_QUEUE_SIZE;//任务池的默认大小
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList();//存放外面传入的任务的地方
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");//线程组对象
    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();//存放线程的数组
    private final DiscardPolicy discardPolicy;
    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This Task");
    };

    public SimpleThreadPool() {
        this(2, 10, DEFAULT_DISCARD_POLICY);//最小值 2
    }

    public SimpleThreadPool(int size, int taskQueueSize, DiscardPolicy discardPolicy) {
        if (size >= MAX_SIZE)
            size = MAX_SIZE;
        this.DEFALUT_SIZE = size;
        this.TASK_QUEUE_SIZE = taskQueueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public void init() {
        //循环构建 DEFALUT_SIZE 个线程并且启动
        for (int i = 0; i < DEFALUT_SIZE; i++) {
            createWorkTask(i + "");
        }
    }

    /**
     * 创建线程，并且让线程启动一直处于等待状态
     *
     * @param name
     */
    public void createWorkTask(String name) {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + seq++);
        task.start();
        THREAD_QUEUE.add(task);
    }

    /**
     * 传入任务，让线程执行
     *
     * @param runnable
     */
    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > TASK_QUEUE_SIZE)
                discardPolicy.discard();
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    public enum TaskStatus {
        FREE,  //自由
        RUNNING,    //运行中
        BLOCKED,    //锁住
        DEAD    //销毁
    }

    public static class WorkerTask extends Thread {
        private volatile TaskStatus taskStatus = TaskStatus.FREE;
//        public WorkerTask(TaskStatus taskStatus) {
//            this.taskStatus = taskStatus;
//        }
        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            OUT:
            while (this.taskStatus != TaskStatus.DEAD) {
                Runnable run;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskStatus = TaskStatus.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break OUT;
                        }
                    }
                    run = TASK_QUEUE.removeFirst();
                }
                //执行任务
                if (run != null) {
                    taskStatus = TaskStatus.RUNNING;
                    run.run();
                    taskStatus = TaskStatus.FREE;
                }
            }
        }

        public void close() {
            this.taskStatus = TaskStatus.DEAD;
        }

        public TaskStatus getTaskStatus() {
            return taskStatus;
        }
    }

    /**
     * 自定义异常当任务数量过大时抛出
     */
    public static class DiscardException extends RuntimeException {
        public DiscardException(String msg) {
            super(msg);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    public void shutdown() throws InterruptedException {
        while (!THREAD_QUEUE.isEmpty()) {
            Thread.sleep(100);
        }
        int inival = THREAD_QUEUE.size();
        while (inival > 0) {
            for (WorkerTask workerTask : THREAD_QUEUE) {
                if (workerTask.getTaskStatus() == TaskStatus.BLOCKED) {
                    workerTask.interrupt();
                    workerTask.close();
                    inival--;
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("The thread stop");
    }

    public int getTASK_QUEUE_SIZE() {
        return TASK_QUEUE_SIZE;
    }

    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(10, 10);
        for (int i = 0; i < 40; i++) {
            int n = i;
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread() + "----" + n);
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
