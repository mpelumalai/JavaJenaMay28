package threads;

public class Ex2 {
    private static volatile boolean stop = false;

    public static void main(String[] args) throws Throwable {
        new Thread(() -> {
            System.out.println("Job starting...");
            while (!stop)
                ;
            System.out.println("Job stopping!!!");
        }).start();
        System.out.println("Started job, waiting...");
        Thread.sleep(1000);
        stop = true;
        System.out.println("Set stop flag, exiting...");
    }
}
