package example.read_write;

public class ReadWorker extends Thread {

    private SharedData sharedData;


    public ReadWorker(SharedData sharedData, String name) {
        super(name);
        this.sharedData = sharedData;
    }


    @Override
    public void run() {
        //这里有个小细节，就是如果  try catch 写在while里面就无法终止while循环，
        // 但是写在外面，就会把while循环给终止了
        try {
            while (true) {
                String str = sharedData.read();
                System.out.println(Thread.currentThread().getName() + "   " + str);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
