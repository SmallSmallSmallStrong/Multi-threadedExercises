package example.duo;

public class TiketRunnble implements Runnable {
    private static final Integer MAX = 100;
    private static Integer index = 0;

    public TiketRunnble() {
    }

    @Override
    public void run() {
        while(index < MAX){
            System.out.println(Thread.currentThread() + "叫号：" + (index++));
            try {
                Thread.sleep(1l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
