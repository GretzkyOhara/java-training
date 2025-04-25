package be.abis.exercise.repository;


import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryPersonRepository implements PersonRepository {

    private List<Person> persons = new ArrayList<Person>();

    public MemoryPersonRepository() {
        Person.counter = 0;
        Address a1 = new Address("Diestsevest", "32/4b", "3000", "Leuven", "België", "BE");
        Address a2 = new Address("Sint-Lazaruslaan", "10", "1210", "Saint-Josse-Ten-Noode", "Belgique", "BE");
        Address a3 = new Address("Avenue du Bourget", "42", "1130", "Brussels", "Belgium", "BE");
        Address a4 = new Address("Amsterdamseweg", "55", "1182GP", "Amstelveen", "Nederland", "NL");

        Company c1 = new Company("ABIS", a1);
        Company c2 = new Company("BNP Paribas Fortis", a2);
        Company c3 = new Company("IBM", a3);
        Company c4 = new Company("KLM", a4);

        Person inst1 = new Person("Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10), "sschillebeeckx@abis.be", "somepass1", "nl", c1);
        Person inst2 = new Person("Koen", "De Backer", LocalDate.of(1962, 11, 25), "kdebacker@abis.be", "somepass2", "nl", c1);
        Person inst3 = new Person("Gie", "Indesteege", LocalDate.of(1958, 8, 19), "gindesteege@abis.be", "somepass3", "nl", c1);
        Person inst4 = new Person("Bart", "Lemarcq", LocalDate.of(1976, 2, 12), "blemarcq@abis.be", "somepass4", "fr", c1);
        Person p1 = new Person("Michel", "Dupont", LocalDate.of(1980, 5, 5), "michel.dupont@bnpparibasfortis.com", "somepass5", "fr", c2);
        Person p2 = new Person("Anne", "Van der Meulen", LocalDate.of(1984, 9, 30), "anne.vandermeulen@bnpparibasfortis.com", "somepass6", "nl", c2);
        Person p3 = new Person("Bob", "Miles", LocalDate.of(1967, 3, 11), "bob.miles@ibm.com", "somepass7", "en", c3);
        Person p4 = new Person("Willem-Alexander", "Janssen", LocalDate.of(1971, 1, 18), "willemalexander.janssen@klm.nl", "somepass8", "nl", c4);
        Person p5 = new Person("Jef", "Smits", LocalDate.of(1988, 10, 10), "jefke@yahoo.com", "somepass9", "nl");

        persons.addAll(Arrays.asList(new Person[]{inst1, inst2, inst3, inst4, p1, p2, p3, p4, p5}));
    }


    @Override
    public List<Person> findAllPersons() {
        return persons;
    }

    @Override
    public Person findPersonById(int id) throws PersonNotFoundException {

        return persons.stream().filter(p -> p.getPersonNumber() == id).findFirst().orElseThrow(() -> new PersonNotFoundException("Person not found"));


    }

    @Override
    public Person findPersonByEmailAndPassword(String email, String password) throws PersonNotFoundException {

        Logger rootLog = LogManager.getRootLogger();
        Logger exceptionLog = LogManager.getLogger("exceptionLogger");

       Optional<Person> person= persons.stream().filter(p -> p.getEmail().equals(email)).filter(p -> p.getPassword().equals(password)).findFirst();

       if (!person.isPresent()) {
           exceptionLog.error("Person by email/password not found");
          throw new PersonNotFoundException("Person by email/password not found");
       } else {
           rootLog.info("Person by email/password found");
           return person.get();
       }

    }

    @Override
    public List<Person> findPersonsForCompany(String companyName) throws PersonNotFoundException {

        List<Person> theResult ;
        theResult = persons.stream().filter(p -> p.getCompany() != null).filter(p -> p.getCompany().getName().equals(companyName)).collect(Collectors.toList());


        if (theResult.size() != 0)
            return theResult;
        else {
            throw new PersonNotFoundException("Persons with given company : " + companyName + " not found");
        }
    }


    @Override
    public void addPerson(Person p) throws PersonAlreadyExistsException {

        if (!persons.contains(p)) {
            persons.add(p);
        }
        else {
            throw new PersonAlreadyExistsException("Person already exists in list");
        }
    }
}
