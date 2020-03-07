package example.observer.listener;

import java.util.List;

public class ObserverLifeCycleListener implements LifeCycleListener {
    private final Object LOCK = new Object();


    // 监管者被通知后，可以通过“主题”传递的信息做一些操作
    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName() + "] data changed and status is " + event.getStatus());
            if (event.getCause() != null) {//如果有异常抛出异常
                System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
                event.getCause().printStackTrace();
            }
        }
    }

    // 为每个线程注册监听者和安排任务
    public void concurrentQuery(List<String> ids) {
        if (ids == null && ids.isEmpty()) {
            return;
        }
        ids.forEach(id -> {
            new Thread(new ObservableRunnable(this) {
                @Override
                public void run() {
                    notifyChange(new RunnableEvent(RunnableStatus.RUNNING, Thread.currentThread(), null));
//                    System.out.println("Query for the id is " + id);
                    System.out.println("Query for the id is " + id);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        notifyChange(new RunnableEvent(RunnableStatus.ERROR, Thread.currentThread(), e));
                    }
                    notifyChange(new RunnableEvent(RunnableStatus.DONE, Thread.currentThread(), null));
                }
            }, id).start();
        });
    }

}
