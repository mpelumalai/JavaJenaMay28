package collectors;

import schoolcleaned.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentCollection {
    public static String getLetterGrade(Student s) {
        int score = s.getGrade();
        if (score > 90) return "A";
        if (score > 80) return "B";
        if (score > 70) return "C";
        if (score > 60) return "D";
        if (score > 50) return "E";
        return "F";
    }

    public static void main(String[] args) {
        List<Student> roster = List.of(
                Student.fromNameGradeCourses("Fred", 65, "Maths", "Physics"),
                Student.fromNameGradeCourses("Jim", 55, "Art"),
                Student.fromNameGradeCourses("Jeremy", 95, "Art"),
                Student.fromNameGradeCourses("Shiela", 65, "Maths", "Astro-physics", "Quantum Mechanics")
        );
        var table = roster.stream()
                .collect(Collectors.groupingBy(s -> getLetterGrade(s),
                        Collectors.mapping(s -> s.getName() + " scored " + s.getGrade(),
                                Collectors.joining(", "))));
        table.forEach((k, v) -> System.out.println("Grade " + k + " scored by " + v));
    }
}
