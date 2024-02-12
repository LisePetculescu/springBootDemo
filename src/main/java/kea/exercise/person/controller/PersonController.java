package kea.exercise.person.controller;

import kea.exercise.person.model.Person;
import kea.exercise.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        Optional<Person> person = personRepository.findById(id);

        return ResponseEntity.of(person);

//        if (person.isPresent()) {
//            return ResponseEntity.ok(person.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }


       // return person.orElse(null);
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        Optional<Person> updatePerson = personRepository.findById(id);

        if (updatePerson.isPresent()) {
            Person originalPerson = updatePerson.get();

            originalPerson.setDateOfBirth(person.getDateOfBirth());
            originalPerson.setLastName(person.getLastName());
            originalPerson.setFirstName(person.getFirstName());

            Person updatedPerson = personRepository.save(originalPerson);
            return ResponseEntity.ok().body(updatedPerson);


        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable int id) {
        Optional<Person> deletePerson = personRepository.findById(id);

        if (deletePerson.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.ok().body(deletePerson.get());
        } else {
            return  ResponseEntity.notFound().build();
        }

    }


}
