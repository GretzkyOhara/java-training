package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.FileCourseRepository;

import java.io.IOException;
import java.time.LocalDate;

public class TestMainIO {

    public static void main(String[] args) {


        FileCourseRepository fcr = null;
        try {
            fcr = new FileCourseRepository();
        } catch (IOException e) {
            e.printStackTrace();
        }

    Course newCourse = new Course("Mastering lego ",1,999.0, LocalDate.of(2025,11,20));

            fcr.addCourse(newCourse);

        // move courses2.csv
            fcr.moveInputFile();
            }




}
