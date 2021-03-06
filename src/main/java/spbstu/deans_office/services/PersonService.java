package spbstu.deans_office.services;

import spbstu.deans_office.DTO.PersonDTO;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.utils.Utils;

import java.util.List;
import java.util.Map;

public interface PersonService {

    void deleteIfAVGMarkLessThan(double threshold);
    void deletePersonWithMinimalAVGMark();
    void addPerson(PersonDTO person);
    void updatePerson(PersonDTO person);
    Double getAvgForPerson(long studentId);
    List<Person> getStudentOrderedByAVGMark();
    List<Person> getPeopleByGroupId(Long group_id);
    List<Person> getPeopleWithMark(Integer value);
    Map<Person, Double> getAVGForTeachers();
    Map<Person, Double> getAVGForStudents();
}
