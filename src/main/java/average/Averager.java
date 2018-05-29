package average;

import java.util.OptionalDouble;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;

class Average {
    private double sum;
    private long count;

    public Average() { }

    public void include(double d) {
        this.sum += d;
        this.count++;
    }

    public void merge(Average other) {
        this.sum += other.sum;
        this.count += other.count;
    }

    public OptionalDouble get() {
        if (count > 0) {
            return OptionalDouble.of(sum/count);
        } else {
            return OptionalDouble.empty();
        }
    }
}

public class Averager {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(4);
        long start = System.nanoTime();
        AtomicLong al = new AtomicLong(0);
        Semaphore sem = new Semaphore(0);
        fjp.execute(() -> {
            DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
                    .limit(60_000_000L)
//                    .parallel()
//                .unordered() // this is unordered anyway :)
                    .map(x -> Math.sin(x))
                    .map(x -> Math.asin(x))
                    .map(x -> Math.sin(x))
                    .collect(
                            () -> new Average(),
                            (b, i) -> b.include(i),
                            (bf, bo) -> bf.merge(bo)
                    ).get()
                    .ifPresentOrElse(x -> System.out.println("Mean is " + x), () -> System.out.println("No data!"));
            al.set(System.nanoTime());
            sem.release();
        });
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        long time = System.nanoTime() - start;
        long time = al.get() - start;
        System.out.printf("Time take is %9.5f\n", (time / 1_000_000_000.0));
    }
}
