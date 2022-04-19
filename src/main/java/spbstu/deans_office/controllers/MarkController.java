package spbstu.deans_office.controllers;

import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.services.MarkService;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mark")
public class MarkController {
    private MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping("/group/{name}/list")
    public ResponseEntity<List<Mark>> getMarksByGroup(@PathVariable("name") String groupName) {
        return new ResponseEntity<>(markService.getMarksByGroup(groupName), HttpStatus.OK);
    }

    @GetMapping("/group/avg")
    public ResponseEntity<Map<String, Double>> getAvgForGroups() {
        return new ResponseEntity<>(markService.getAvgForGroups(), HttpStatus.OK);
    }

    @GetMapping("/subject/{name}/list")
    public ResponseEntity<List<Mark>> getMarksBySubject(@PathVariable("name") String subjectName) {
        return new ResponseEntity<>(markService.getMarksBySubject(subjectName), HttpStatus.OK);
    }

    @GetMapping("/subject/avg")
    public ResponseEntity<Map<String, Double>> getAvgForSubjects() {
        return new ResponseEntity<>(markService.getAvgForSubjects(), HttpStatus.OK);
    }

    @GetMapping("/teacher/{id}/list")
    public ResponseEntity<List<Mark>> getMarksByTeacher(@PathVariable("id") long teacherId) {
        return new ResponseEntity<>(markService.getMarksByTeacher(teacherId), HttpStatus.OK);
    }

    @GetMapping("/student/{id}/list")
    public ResponseEntity<List<Mark>> getMarksByStudent(@PathVariable("id") long id) {
        return new ResponseEntity<>(markService.getMarksByStudent(id), HttpStatus.OK);
    }

    @GetMapping("/student/{id}/avg")
    public ResponseEntity<Map<String, Double>> getAvgForSubjects(@PathVariable("id") long id) {
        return new ResponseEntity<>(markService.getMarksAVGByStudentOnAllSubjects(id), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateMark(@RequestBody MarkDTO mark) {
        markService.updateMark(mark);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMark(@RequestBody MarkDTO mark) {
        markService.addMark(mark);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMark(@PathVariable long id) {
        markService.deleteById(id);
    }
}
