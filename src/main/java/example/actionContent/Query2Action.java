package example.actionContent;

public class Query2Action {

    public void execute(){
        try {
            Thread.sleep(1000);//假设执行的效率比较低
            ActionContent.getActionContent().getContent().setIdCard("111_" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
