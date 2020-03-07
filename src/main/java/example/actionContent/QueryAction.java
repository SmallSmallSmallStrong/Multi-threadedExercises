package example.actionContent;

public class QueryAction {

    public void execute(){
        try {
            Thread.sleep(1000);//假设执行的效率比较低
            ActionContent.getActionContent().getContent().setName("小妹_" + Thread.currentThread().getName()  );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
