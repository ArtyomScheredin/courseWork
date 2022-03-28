package spbstu.deans_office.services;

import spbstu.deans_office.DTO.PersonDTO;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.utils.Utils;

import java.util.List;

public interface PersonService {
    List<Person> getAllLimitBy(Integer limit);
    Double getAvgForPerson(long student_id);
    List<Person> getStudentOrderedByAVGMark();
    void deleteIfAVGMarkLessThan(double threshold);
    void deletePersonWithMinimalAVGMark();
    void addPerson(PersonDTO person);
    void updatePerson(PersonDTO person);
    List<Person> getPeopleByGroupId(Long group_id);
    List<Person> getPeopleWithMark(Integer value);
    List<Utils.Pair<Person, Double>> getAVGForTeachers();
}
