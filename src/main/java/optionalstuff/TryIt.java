package optionalstuff;

import java.util.Map;
import java.util.Optional;

public class TryIt {
    public static void main(String[] args) {
        Optional.of(Map.of("Fred", "Jones", "Jim", "Smit"))
                .map(m -> m.get("Freddy"))
                .map(m -> m.toUpperCase())
                .ifPresentOrElse(s -> System.out.println("Found: " + s),
                        () -> System.out.println("Didn't find anything"));
    }
}
