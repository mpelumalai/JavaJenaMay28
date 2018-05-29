package schoolcleaned;

import java.util.Collections;
import java.util.List;

@FunctionalInterface
interface Criterion<E>/* extends Blah*/ {
    boolean test(E s);
    public static Criterion negate(Criterion crit) {
        return s -> !crit.test(s);
    }
    default Criterion<E> negate() {
        return s -> !this.test(s);
    }
    default Criterion<E> and(Criterion<E> other) {
        return s -> this.test(s) && other.test(s);
    }
}

public final class Student {
    private final String name;
    private final int grade;
    private final List<String> courses;

    private static Criterion<Student> enthusiasticCriterion = s -> s.courses.size() > 1;

    public static Criterion<Student> getEnthusiasticCriterion() {
        return enthusiasticCriterion;
    }

    public static Criterion<Student> getSmartCriterion(/*final*/ int threshold) {
        return s -> s.getGrade() > threshold;
    }

    private Student(String name, int grade, List<String> courses) {
        this.name = name;
        this.grade = grade;
        this.courses = courses;
    }

    public static Student fromNameGradeCourses(String name, int grade, String... courses) {
        Student rv = new Student(name, grade,
                Collections.unmodifiableList(
                        Collections.checkedList(List.of(courses), String.class)
                )
        );
        return rv;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public List<String> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", courses=" + courses +
                '}';
    }
}
