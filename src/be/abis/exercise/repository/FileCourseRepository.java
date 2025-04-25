package be.abis.exercise.repository;

import be.abis.exercise.model.Course;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCourseRepository implements CourseRepository {

    List<Course> courses = new ArrayList<Course>();


    public FileCourseRepository() throws IOException {
    readInputFile();

    }

    private void readInputFile() throws IOException {

        Stream<String> allLines = Files.lines(Paths.get("C://temp//javacourses//courses.csv"));

        courses = allLines.map(s -> this.parseCourse(s) ).collect(Collectors.toList());

    }
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public List<Course> findAllCourses() {
        return List.of();
    }

    @Override
    public void addCourse(Course c) {


        Path path = Paths.get("C://temp//javacourses//courses.csv");
        try (

                PrintWriter writer = new PrintWriter (Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.APPEND)))
                      {
            writer.println(formatCourse(c));
        } catch (IOException e) {
            e.printStackTrace();
        }


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

    }

    @Override
    public Course parseCourse(String s) {

        Course theResult = new Course();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy");

        String[] tokens = s.split(";");

        theResult.setTitle(tokens[0]);
        theResult.setDays(Integer.parseInt(tokens[1]));
        theResult.setDailyPrice(Double.valueOf(tokens[2]));
        theResult.setReleaseDate(LocalDate.parse(tokens[3],fmt));

        return theResult;
    }

    public void moveInputFile () {
        try {

            if (!Files.exists(Paths.get("C://temp//javacourses//inputfiles//")))
                 Files.createDirectory(Paths.get("C://temp//javacourses//inputfiles//"));

        Path source = Paths.get("C://temp//javacourses//courses2.csv");
        Path dest = Paths.get("C://temp//javacourses//inputfiles//courses2.csv");

        if(Files.exists(source))
            Files.move(source,dest);

        } catch (IOException e) {
            e.printStackTrace();
        }





    }

}
