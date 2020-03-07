package example.observer;

import java.util.ArrayList;

public class Subject {
    private int status;
    private ArrayList<OBserver> observers = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void attach(OBserver observer) {
        observers.add(observer);
    }

    public void setStatus(int status) {
        if (this.status == status) {//避免重复赋值造成状态重复更新
            return;
        }
        this.status = status;
        notifyAllObservers();
    }

    //唤醒 OBserver 的更新操作发送消息
    private void notifyAllObservers() {
        observers.forEach(OBserver::update);
    }

}
