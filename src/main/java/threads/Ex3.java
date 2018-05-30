package threads;

public class Ex3 {
    private static volatile long count = 0;

    public static void main(String[] args) throws Throwable {
        Runnable r = () -> {
            for (int i = 0; i < 10_000; i++) {
                synchronized (Ex3.class) {
                    count++;
                }
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Count is " + count);
    }
}
