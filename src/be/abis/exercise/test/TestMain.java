package be.abis.exercise.test;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMain {

    public static void main(String[] args) {

        PersonRepository pr = new MemoryPersonRepository();
        List<Person> persons = pr.findAllPersons();

        System.out.println("All persons:");
        persons.forEach(System.out::println);

        System.out.println("------");
        System.out.println("Person by id happy flow");
        System.out.println("------");
        try {

            System.out.println(pr.findPersonById(1));
        } catch (PersonNotFoundException e) {
        }


        System.out.println("------");
        System.out.println("Person by id bad flow");
        System.out.println("------");
        try {

            System.out.println(pr.findPersonById(10));
        } catch (PersonNotFoundException e) {
                    }

        System.out.println("------");
        System.out.println("Person by email/password happy flow");
        System.out.println("------");
        try {

            System.out.println(pr.findPersonByEmailAndPassword("sschillebeeckx@abis.be","somepass1"));
        } catch (PersonNotFoundException e) {
        }

        System.out.println("------");
        System.out.println("Person by email/password bad flow");
        System.out.println("------");
        try {

            System.out.println(pr.findPersonByEmailAndPassword("bert.c@ing.be","abc"));
        } catch (PersonNotFoundException e) {
        }

        System.out.println("------");
        System.out.println("Persons by company happy flow");
        System.out.println("------");

        try {
            for (Person p  : pr.findPersonsForCompany("ABIS"))
                System.out.println(p);
        } catch (PersonNotFoundException e) {

        }


        System.out.println("------");
        System.out.println("Persons by company bad flow");
        System.out.println("------");

        try {
            for (Person p  : pr.findPersonsForCompany("Plopsa"))
                System.out.println(p);
        } catch (PersonNotFoundException e) {
                    }

        CourseRepository cr = new MemoryCourseRepository();
        List<Course> courses = cr.findAllCourses();

        System.out.println("\nAll courses:");
        courses.forEach(System.out::println);

        System.out.println("\nAll courses / naturally sorted:");

        courses.stream().sorted().forEach(System.out::println);

        System.out.println("\nAll courses / sorted by days:");

        courses.stream().sorted(new Course.CourseByDaysComparator()).forEach(System.out::println);


        System.out.println("\nAll courses / sorted by daily price:");

        courses.stream().sorted(new Course.CourseByDailyPriceComparator()).forEach(System.out::println);


        System.out.println("\nSort courses by title  :");

        courses.sort(Comparator.comparing(Course::getTitle));

        for (Course c : courses) {
            System.out.println(c);
        }

        System.out.println("\nSort courses by duration , then by daily price  :");
        courses.sort(Comparator.comparing(Course::getDays).thenComparing(Course::getDailyPrice));

        for (Course c : courses) {
            System.out.println(c);
        }

        System.out.println("\nPersons whose last name starts with S alphabetically :");

       persons.stream().filter(p -> p.getLastName().startsWith("S")).sorted(Comparator.comparing(Person::getFirstName)).forEach(System.out::println);


        System.out.println("\nRemove all courses that take less than 3 days :");

      //  courses.removeIf(c -> c.getDays() < 3);
        courses.forEach(System.out::println);

        System.out.println("\nPrint a list of all distinct companies :");

        persons.stream().filter (p -> p.getCompany() != null).map(p -> p.getCompany().getName()).distinct().forEach(System.out::println);


        System.out.println("\nHow many persons work in Leuven");

        Long personLeuvenCount =persons.stream().filter(p -> p.getCompany() != null).filter (p -> p.getCompany().getAddress().getTown().contains("Leuven")).count();

        System.out.println(personLeuvenCount);

        System.out.println("\nWho is the youngest person");
        Optional <Person> youngestPerson = persons.stream().max(Comparator.comparing(Person::getBirthDate));
        Person youngPerson = youngestPerson.get();
        System.out.println(youngPerson);

        System.out.println("\nGroup all persons per company");

        Map theMap = persons.stream().filter (p -> p.getCompany() != null).collect(Collectors.groupingBy(Person::getCompany));
        System.out.println(theMap);




        System.out.println("\nAdd person equals/hashcode exercise 1");
        try {
            Person p1 = new Person("Bert", "Christiaens", LocalDate.of(1982, 1, 5), "bert.christiaens@proximus.be", "test01", "nl");
            pr.addPerson(p1);
            pr.addPerson(p1);
            Person p2 = new Person("Bert", "Christiaens", LocalDate.of(1982, 1, 5), "bert.christiaens@proximus.be", "test01", "nl");
            pr.addPerson(p2);

        } catch (PersonAlreadyExistsException e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
                  }


        System.out.println("\nString handling exercise 1");

        System.out.println(cr.formatCourse(cr.findAllCourses().getFirst()));

        System.out.println("\nString handling exercise 2");

        System.out.println("formatting exercise");
        cr.printAllCourses();




    }


}

