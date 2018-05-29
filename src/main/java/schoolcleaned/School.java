package schoolcleaned;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class School {

    public static <E> List<E> getByCriterion(Iterable<E> ls, Criterion<? super E> crit) {
        List<E> out = new ArrayList<>();
        for (E s : ls) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

    public static <E> void show(List<E> ls) {
        for (E s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        List<Student> roster = List.of(
                Student.fromNameGradeCourses("Fred", 65, "Maths", "Physics"),
                Student.fromNameGradeCourses("Jim", 55, "Art"),
                Student.fromNameGradeCourses("Jeremy", 95, "Art"),
                Student.fromNameGradeCourses("Shiela", 65, "Maths", "Astro-physics", "Quantum Mechanics")
        );

        int[] count = {0};
        Stream<String> ss = roster.stream()
                .peek(s -> System.out.println(">> " + s))
                .flatMap(s -> s.getCourses().stream().map(t -> s.getName() + " : " + t))
                .peek(s -> System.out.println("-- " + s))
        // NONONONONONO!!! do not mutate "external" data
        // Indeed, don't mutate anything, make new objects.
//                .peek(s -> {count[0]++; return;})
                ;

                ss.forEach(s -> System.out.println(s));
                ss.forEach(s -> System.out.println(s));
    }

}
