package spbstu.deans_office.controllers;

import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.DTO.PersonDTO;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.services.PersonService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @DeleteMapping("/avg-lower/{threshold}")
    public void deleteIfAVGMarkLessThan(@PathVariable double threshold) {
        personService.deleteIfAVGMarkLessThan(threshold);
    }

    @DeleteMapping("/worst")
    public void deletePersonWithMinimalAVGMark() {
        personService.deletePersonWithMinimalAVGMark();
    }

    @PutMapping(consumes = "application/json")
    public void updateMark(@RequestBody PersonDTO person) {
        personService.updatePerson(person);
    }

    @PostMapping(consumes = "application/json")
    public void addPerson(@RequestBody PersonDTO person) {
        personService.addPerson(person);
    }

    @GetMapping("/{id}/avg")
    public ResponseEntity<Double> getAvgForPerson(@PathVariable long id) {
        Double average = personService.getAvgForPerson(id);
        return new ResponseEntity<>(average, HttpStatus.OK);
    }

    @GetMapping("/ordered-list/by-mark")
    public ResponseEntity<List<Person>> getStudentOrderedByAVGMark() {
        List<Person> result = personService.getStudentOrderedByAVGMark();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/from-group/{id}")
    public ResponseEntity<List<Person>> getPeopleByGroupId(@PathVariable long id) {
        List<Person> result = personService.getPeopleByGroupId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/with-mark/{value}")
    public ResponseEntity<List<Person>> getPeopleWithMark(@PathVariable int value) {
        List<Person> result = personService.getPeopleWithMark(value);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/teacher/avg")
    public ResponseEntity<Map<Person, Double>> getAVGForTeachers() {
        Map<Person, Double> result = personService.getAVGForTeachers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/student/avg")
    public ResponseEntity<Map<Person, Double>> getAVGForStudents() {
        Map<Person, Double> result = personService.getAVGForStudents();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
