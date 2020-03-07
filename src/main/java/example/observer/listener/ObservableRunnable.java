package example.observer.listener;

/**
 *
 */
public abstract  class ObservableRunnable implements Runnable {

    private final ObserverLifeCycleListener listener;

    // 注册监管者
    public ObservableRunnable(ObserverLifeCycleListener listener) {
        this.listener = listener;
    }

    // 使用监管者的onEvent()方法来进行通知
    public void notifyChange(RunnableEvent event) {
        listener.onEvent(event);
    }


    public enum RunnableStatus {
        RUNNING, ERROR, DONE
    }

    public static class RunnableEvent {
        private final RunnableStatus status;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableStatus status, Thread thread, Throwable cause) {
            this.status = status;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableStatus getStatus() {
            return status;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }

}
