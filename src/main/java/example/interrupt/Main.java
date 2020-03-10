package example.interrupt;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ThreadService threadService = new ThreadService();
        threadService.execute(() -> {
            while (true) {


            }
        });
        threadService.shutdown(10);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
