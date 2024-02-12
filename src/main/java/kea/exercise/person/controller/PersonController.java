package kea.exercise.person.controller;

import kea.exercise.person.model.Person;
import kea.exercise.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    // @Autowired
    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {

        List<Person> persons = personRepository.findAll();
        return persons;

    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable int id) {
        Optional<Person> person = personRepository.findById(id);

        return person.orElse(null);
    }


}
