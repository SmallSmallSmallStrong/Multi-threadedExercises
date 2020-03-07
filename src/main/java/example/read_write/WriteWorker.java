package example.read_write;

import java.util.Arrays;

public class WriteWorker extends Thread {
    private SharedData sharedData;
    private String string;
    private int index = 0;

    /**
     * @param sharedData 缓冲区
     * @param name       线程名
     * @param string     要写的内容
     */
    public WriteWorker(SharedData sharedData, String name, String string) {
        super(name);
        this.sharedData = sharedData;
        this.string = string;
    }


    @Override
    public void run() {
        try {
//            for (int i = 0; i < string.length(); i++) {
            while (true) {
                sharedData.write(string.charAt(next()));
                System.out.println(Thread.currentThread().getName() + "   " + Arrays.toString(sharedData.getBuffer()));
                Thread.sleep(100);
            }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int next() {
        index++;
        if (index > string.length() - 1) {
            index = 0;
        }
        return index;
    }
}
