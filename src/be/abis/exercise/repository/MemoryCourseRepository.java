package be.abis.exercise.repository;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;

import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MemoryCourseRepository implements CourseRepository{

    private List<Course> courses = new ArrayList<>();

    public MemoryCourseRepository(){
        courses.add(new Course("DB2, an overview",5,550.0, LocalDate.of(1986,4,30)));
        courses.add(new Course("Workshop SQL",2,475.0, LocalDate.of(1990,1,9)));
        courses.add(new Course("Java Programming",5,500.0, LocalDate.of(1997,5,27)));
        courses.add(new Course("Maven",1,450.0, LocalDate.of(2007,6,11)));
        courses.add(new Course("Programming with Spring",3,525.0, LocalDate.of(2008,3,21)));

    }

    @Override
    public List<Course> findAllCourses() {
        return courses;
    }

    @Override
    public void addCourse(Course c) {
        courses.add(c);

         }

    @Override
    public String formatCourse(Course c) {

        StringBuilder sb = new StringBuilder();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/YYYY");

        sb.append(c.getTitle())
                .append(";")
                .append(c.getDays())
                .append(";")
                .append(c.getDailyPrice())
                .append(";")
                .append(fmt.format(c.getReleaseDate()));

        return sb.toString();
    }


    @Override
    public void printAllCourses() {


        StringBuilder dashes = new StringBuilder("");
        for (int i = 1; i <= 70; i++) {
            dashes.append("-");
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        //HEADER
        System.out.printf("%1$-70s\n",dashes.toString());
        System.out.printf("%1$-70s\n","---------------------------Course Overview---------------------------");
        System.out.printf("%1$-70s\n",dashes.toString());
        System.out.printf("%1$-25s%2$-25s%3$-20s\n","Course Title","Total Price with VAT","Release Date");
        System.out.printf("%1$-70s\n",dashes.toString());
        //COURSE INFO
        for (Course c: courses){
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("nl","BE"));


            System.out.printf("%1$-25s%2$-25s%3$-20s\n",c.getTitle(),nf.format(c.calculateTotalPrice()),fmt.format(c.getReleaseDate()));
        }






    }

    @Override
    public Course parseCourse(String s) {
        return null;
    }
}
