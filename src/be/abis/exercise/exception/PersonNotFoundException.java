package be.abis.exercise.exception;

public class PersonNotFoundException extends Exception {

    public PersonNotFoundException() {
    }

    public PersonNotFoundException(String message) {

        System.out.println(message);
    }
}
