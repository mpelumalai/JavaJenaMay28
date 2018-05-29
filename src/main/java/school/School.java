package school;

import java.util.ArrayList;
import java.util.List;

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

//    public static List<Student> getSmartStudents(List<Student> ls, int threshold) {
//        List<Student> out = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getGrade() > threshold) {
//                out.add(s);
//            }
//        }
//        return out;
//    }
//

    public static <E> void show(List<E> ls) {
        for (E s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
//        Student.fromNameGradeCourses("Fred", 0);
        List<Student> roster = List.of(
                Student.fromNameGradeCourses("Fred", 65, "Maths", "Physics"),
                Student.fromNameGradeCourses("Jim", 55, "Art"),
                Student.fromNameGradeCourses("Jeremy", 95, "Art"),
                Student.fromNameGradeCourses("Shiela", 65, "Maths", "Astro-physics", "Quantum Mechanics")
        );

        System.out.println("All");
        show(roster);
        System.out.println("Enthusiastic");
        show(getByCriterion(roster, Student.getEnthusiasticCriterion()));
//        show(getSmartStudents(roster, 50));
        System.out.println("Smarter than 75");
        show(getByCriterion(roster, Student.getSmartCriterion(75)));

        System.out.println("Not... smarter than 75");
        Criterion smart = Student.getSmartCriterion(75);
//        Criterion notSmart = Criterion.negate(smart);
        Criterion notSmart = smart.negate();

        show(getByCriterion(roster, notSmart));

        Criterion<CharSequence> ccs = s -> s.length() > 3;
        List<String> names = List.of("Fred", "Jim", "Sheila");
        show(getByCriterion(names, ccs));

    }

}
