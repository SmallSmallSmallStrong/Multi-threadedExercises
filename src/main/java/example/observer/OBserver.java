package example.observer;

public abstract class OBserver  {

    protected Subject subject;

    public OBserver(Subject subject){
        this.subject = subject;
        subject.attach(this);
    }

    public abstract void update();

}
