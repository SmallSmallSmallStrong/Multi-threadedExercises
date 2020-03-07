package example.actionContent;

public class ActionContent {

    private static final ThreadLocal<Content> threadLocal = new ThreadLocal() {
        @Override
        protected Content initialValue() {
            return new Content();
        }
    };

    private static class ContentHolder {
        private final static ActionContent actionContent = new ActionContent();
    }

    public   Content getContent() {
        return threadLocal.get();
    }


    public static ActionContent getActionContent() {
        return ContentHolder.actionContent;
    }
}
