package example.actionContent;

public class ExecuteTask implements Runnable {
    private QueryAction queryAction = new QueryAction();
    private Query2Action query2Action = new Query2Action();

    @Override
    public void run() {
        Content content = ActionContent.getActionContent().getContent();
        queryAction.execute();//设置name
        query2Action.execute();//设置IdCard
        System.out.println(content.toString());
    }
}
