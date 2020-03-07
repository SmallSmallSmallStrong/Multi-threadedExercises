package example.observer.listener;

//生命循环监听
public interface LifeCycleListener {
    void onEvent(ObservableRunnable.RunnableEvent event);
}
