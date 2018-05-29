package school;

import java.util.*;

interface Blah {
    void stupid();
}

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

//            (Student s) -> { return s.courses.size() > 1; };

//    private static Criterion enthusiasticCriterion = /*new Criterion() {*/
//       /* @Override*/
//        /*public boolean test*/(Student s) -> {
//            return s.courses.size() > 1;
//        }
//    /*}*/;

//    private static Criterion enthusiasticCriterion = new /*EnthusiasticStudentCriterion();
//    private static class EnthusiasticStudentCriterion implements */ Criterion() {
//        @Override
//        public boolean test(Student s) {
//            return s.courses.size() > 1;
//        }
//    };

    //    private static Criterion enthusiasticCriterion = new EnthusiasticStudentCriterion();
//    private static class EnthusiasticStudentCriterion implements Criterion {
//        @Override
//        public boolean test(Student s) {
//            return s.courses.size() > 1;
//        }
//    }
//
    public static Criterion<Student> getEnthusiasticCriterion() {
        return enthusiasticCriterion;
    }

    public static Criterion<Student> getSmartCriterion(/*final*/ int threshold) {
//        threshold = threshold + 1;
        return s -> s.getGrade() > threshold;
    }

//    static class SmartStudentCriterion implements Criterion {
//        @Override
//        public boolean test(Student s) {
//            return s.grade > 60;
//        }
//    }
//

    //    private void badList(List l) {
//        l.add(LocalDate.now());
//    }
    private Student(String name, int grade, List<String> courses) {
        // validate???
        this.name = name;
        this.grade = grade;
        this.courses = courses;
//        badList(courses);
    }

    public static Student fromNameGradeCourses(String name, int grade, String... courses) {
        Student rv = new Student(name, grade,
                Collections.unmodifiableList(
//                        Collections.checkedList(Arrays.asList(courses), String.class)
                        Collections.checkedList(List.of(courses), String.class)
                )
        );
//        Student rv = new Student(name, grade, List.of(courses));
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

//    If we want "no duplicates" a Set is appropriate
//    However, we must check for successful insertion
//    and must provide elements that adhere to the contract required
//    equals and hashcode, or "order"
//    private TreeSet<String> courses;


// in a DATE class!!
// API should self-protect (encapsulation)
// throw runtime exceptions for "programming errors"
// throw checked exceptions for "you should handle this" situations
// Even more importantly, provide APIs (such as "setEndOfMonth") that
// are hard to misuse--mimimize the requirement for the client programmer
// to actually understand business-logic requirements
//    public void setDayOfMonth(int d) {
//        this.day = d;
