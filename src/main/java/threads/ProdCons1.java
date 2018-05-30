package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdCons1 {
    public static void main(String[] args) {
        final BlockingQueue<int[]> q = new ArrayBlockingQueue<>(10);

        Runnable prod = () -> {
            try {
                for (int i = 0; i < 10_000; i++) {
                    int[] ia = {i, 0};
                    if (i < 100) {
                        Thread.sleep(1);
                    }
                    if (i == 1234) ia[0] = 9; // test the test with a known error
                    ia[1] = i;
                    q.put(ia); ia = null;
                }
                System.out.println("Producer completed...");
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted, shutting down");;
            }

        };

        Runnable cons = () -> {
            try {
                for (int i = 0; i < 10_000; i++) {
                    int [] ia = q.take();
                    if (ia[0] != i || ia[1] != i) {
                        System.out.printf("ERROR!!! read %d and %d at index %d\n", ia[0], ia[1], i);
                    }
                    if (i > 9900) {
                        Thread.sleep(1);
                    }
                }
                System.out.println("Consumer completed...");
            } catch (InterruptedException ie) {
                System.out.println("Consumer interrupted, shutting down");
            }
        };
        new Thread(prod).start();
        new Thread(cons).start();
    }
}
