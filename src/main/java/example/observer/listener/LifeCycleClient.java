package example.observer.listener;

import java.util.Arrays;

public class LifeCycleClient {
    public static void main(String[] args) {
        new ObserverLifeCycleListener().concurrentQuery(Arrays.asList("张三","李四","王五"));
    }
}
