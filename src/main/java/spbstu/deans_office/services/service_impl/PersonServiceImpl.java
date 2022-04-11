package spbstu.deans_office.services.service_impl;

import spbstu.deans_office.DTO.PersonDTO;
import spbstu.deans_office.exceptions.ApiRequestException;
import spbstu.deans_office.models.Group;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.repositories.GroupRepository;
import spbstu.deans_office.repositories.MarkRepository;
import spbstu.deans_office.repositories.PersonRepository;
import spbstu.deans_office.repositories.SubjectRepository;
import spbstu.deans_office.services.PersonService;
import spbstu.deans_office.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private final MarkRepository markRepository;
    private final PersonRepository personRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public PersonServiceImpl(MarkRepository markRepository, PersonRepository personRepository, SubjectRepository subjectRepository, GroupRepository groupRepository) {
        this.markRepository = markRepository;
        this.personRepository = personRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public Double getAvgForPerson(long student_id) {
        if (personRepository.existsById(student_id)) {
            throw new ApiRequestException(Utils.WRONG_STUDENT_ID_MESSAGE + student_id);
        }
        return markRepository.getAVGForPerson(student_id);
    }

    @Override
    public Map<String, Double> getAVGForStudents() {
        HashMap<String, Double> result = new HashMap<>();
        List<Person> teachers = personRepository.findAllByType('s');
        teachers.forEach(person -> {
            String name = person.getLastName();
            Double avgMarks = markRepository.getAVGForStudent(person.getPersonId());
            result.put(name, avgMarks);
        });
        return result;
    }

    @Override
    public List<Person> getStudentOrderedByAVGMark() {
        return markRepository.getStudentOrderedByAVGMark();
    }

    @Override
    public void deleteIfAVGMarkLessThan(double threshold) {
        if (threshold < 0) {
            throw new ApiRequestException("Incorrect threshold");
        }
        Iterable<Person> people = markRepository.getStudentWithAVGMarkLesserThan(threshold);
        personRepository.deleteAll(people);
    }

    @Override
    public void deletePersonWithMinimalAVGMark() {
        Iterable<Person> people = markRepository.getStudentOrderedByAVGMark();
        if (!people.iterator().hasNext()) {
            throw new ApiRequestException("No people in database!");
        }
        personRepository.delete(people.iterator().next());
    }

    @Override
    public void addPerson(PersonDTO person) {
        Person personToInsert;
        Group group = groupRepository.findById(person.group_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE + person.group_id()));
        if (person.person_id() == null) {
            personToInsert = new Person(person.first_name(),
                    person.last_name(), person.patronymic(), group, person.type());
        } else {
            personToInsert = new Person(person.person_id(), person.first_name(),
                    person.last_name(), person.patronymic(), group, person.type());
        }
        personRepository.save(personToInsert);
    }

    @Override
    public void updatePerson(PersonDTO person) {
        if (!personRepository.existsById(person.person_id())) {
            throw new ApiRequestException("WRONG person_id");
        }
        Group group = groupRepository.findById(person.group_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE + person.group_id()));
        personRepository.save(new Person(person.person_id(), person.first_name(),
                person.last_name(), person.patronymic(), group, person.type()));
    }

    @Override
    public List<Person> getPeopleByGroupId(Long group_id) {
        return personRepository.findAllByGroup(group_id);
    }

    @Override
    public List<Person> getPeopleWithMark(Integer value) {
        if ((value < Utils.MARK_VALUE_LOWER_BOUND) || (value > Utils.MARK_VALUE_HIGHER_BOUND)) {
            throw new ApiRequestException("WRONG mark value");
        }
        return markRepository.findAllByValue(value);
    }

    @Override
    public Map<String, Double> getAVGForTeachers() {
        HashMap<String, Double> result = new HashMap<>();
        List<Person> teachers = personRepository.findAllByType('t');
        teachers.forEach(person -> {
            String name = person.getLastName();
            Double avgMarks = markRepository.getAVGForTeacher(person.getPersonId());
            result.put(name, avgMarks);
        });
        return result;
    }
}
