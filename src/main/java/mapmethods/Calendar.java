package mapmethods;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

public class Calendar {
    public static void main(String[] args) {
        Map<LocalDate, String> home = new HashMap<>();
        Map<LocalDate, String> work = new HashMap<>();
        Map<LocalDate, String> combined = new HashMap<>();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate weekend = today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        home.put(tomorrow, "Clean up");
        home.put(weekend, "Party");
        work.put(today, "Finish project");
        work.put(tomorrow, "Leave early");


        home.forEach((k,v) -> System.out.println(k + " : " + v));
        work.forEach((k,v) -> System.out.println(k + " : " + v));
    }
}
