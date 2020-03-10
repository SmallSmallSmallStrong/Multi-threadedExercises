package example.duo;

public class Test {
    public static void main(String[] args) {
        TiketRunnble tiketRunnble = new TiketRunnble();
        Thread t1 = new Thread(tiketRunnble, "1");
        Thread t2 = new Thread(tiketRunnble, "2");
        Thread t3 = new Thread(tiketRunnble, "3");
        Thread t4 = new Thread(tiketRunnble, "4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
