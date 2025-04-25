package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

public class TestMain2 {

    public static void main(String[] args) {

        LocalDate now = LocalDate.now();

        System.out.println("\nDate Time 3 - calculateAge");
        Person p1 = new Person("Michel", "Dupont", LocalDate.of(1980, 5, 5), "michel.dupont@bnpparibasfortis.com", "somepass5", "fr");

        System.out.println(p1.calculateAge());

        System.out.println("\nDate Time 4a");
        LocalDate calculated = now.plusYears(3).plusMonths(2).plusDays(15);
        System.out.println(calculated);

        System.out.println("\nDate Time 4b");
        LocalDate myBirthDate =  LocalDate.of(1982, 1, 5);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEEE", new Locale("nl"));

        System.out.println("Ik ben geboren op een "+ fmt.format(myBirthDate));

        System.out.println("\nDate Time 4d");

        System.out.println("How many days old : "+ ChronoUnit.DAYS.between(myBirthDate, LocalDate.now()));







    }
}
