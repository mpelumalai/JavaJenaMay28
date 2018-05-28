package school;

import java.util.ArrayList;
import java.util.List;

public class School {

    public static List<Student> getStudentsByCriterion(List<Student> ls, StudentCriterion crit) {
        List<Student> out = new ArrayList<>();
        for (Student s : ls) {
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

    public static void showStudents(List<Student> ls) {
        for (Student s : ls) {
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

        showStudents(roster);
//        showStudents(getSmartStudents(roster, 50));
        showStudents(getStudentsByCriterion(roster, Student.getSmartCriterion()));
        showStudents(getStudentsByCriterion(roster, Student.getEnthusiasticStudentCriterion()));
    }

}
