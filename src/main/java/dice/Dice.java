package dice;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dice {
    public static void main(String[] args) {
        Map<Integer, Long> map = IntStream.generate(() -> IntStream.generate(()->
                                                    ThreadLocalRandom.current().nextInt(1, 7))
                                                    .limit(10)
                                                    .sum())
                .limit(100_000)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long longest = map.values().stream().mapToLong(x -> x).max().getAsLong();
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf("%3d: %s\n", e.getKey(), Stream.generate(() -> "*").limit(e.getValue() * 100 / longest).collect(Collectors.joining())));
    }
}
