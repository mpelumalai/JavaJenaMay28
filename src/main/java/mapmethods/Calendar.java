package mapmethods;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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

        combined.putAll(home);
//        home.forEach((k,v) -> combined.merge(k, v, (v1, v2) -> v1 + ", " + v2));
        work.forEach((k,v) -> combined.merge(k, v, (v1, v2) -> v1 + ", " + v2));
        System.out.println("Combined calendar");
        combined.forEach((k,v) -> System.out.println(k + " : " + v));

        System.out.println("or again...");
        Comparator<Map.Entry<LocalDate, String>> cmelds = Comparator.comparing(e -> e.getKey()).reversed();
//        cmelds = cmelds.reversed();

        List<Map.Entry<LocalDate, String>> entries = new ArrayList<>(combined.entrySet());
//        entries.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
        entries.sort(cmelds);
        entries.forEach(e -> System.out.println("On " + e.getKey() + " " + e.getValue()));
    }
}
