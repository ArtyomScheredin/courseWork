package spbstu.deans_office.services.service_impl;

import spbstu.deans_office.DTO.PersonDTO;
import spbstu.deans_office.exceptions.ApiRequestException;
import spbstu.deans_office.models.Group;
import spbstu.deans_office.models.Mark;
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
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static spbstu.deans_office.utils.Utils.WRONG_GROUP_ID;

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
    public Double getAvgForPerson(long studentId) {
        Optional<Person> optional = personRepository.findById(studentId);
        Person person = optional.orElseThrow(() -> new ApiRequestException(Utils.WRONG_STUDENT_ID));

        if (!person.getType().equals('s')) {
            throw new ApiRequestException(Utils.WRONG_STUDENT_ID);
        }
        return markRepository.getAVGForPerson(studentId);
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
    public void updatePerson(PersonDTO dto) {
        Optional<Person> optionalPerson = personRepository.findById(dto.personId());
        Person person = optionalPerson.orElseGet(Person::new);
        Person result = convertPersonDTOToPerson(dto, person);
        personRepository.save(result);
    }

    @Override
    public void addPerson(PersonDTO dto) {
        Person person = new Person();
        Person result = convertPersonDTOToPerson(dto, person);
        personRepository.save(result);
    }

    private Person convertPersonDTOToPerson(PersonDTO dto, Person person) throws ApiRequestException {
        Group group = groupRepository.findById(dto.groupId()).orElseThrow(() -> new ApiRequestException(WRONG_GROUP_ID));
        if ((person.getType() != null) && !dto.type().equals(person.getType())) {
            Set<Mark> marksToDelete = markRepository.findAllByStudent(person);
            marksToDelete.addAll(markRepository.findAllByTeacher(person));
            markRepository.deleteAll(marksToDelete);
        }
        person.setType(dto.type());
        person.setGroup(group);
        person.setFirstName(dto.firstName());
        person.setLastName(dto.lastName());
        person.setPatronymic(dto.patronymic());
        return person;
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
    public Map<Person, Double> getAVGForTeachers() {
        HashMap<Person, Double> result = new HashMap<>();
        List<Person> teachers = personRepository.findAllByType('t');
        teachers.forEach(person -> {
            String name = person.getLastName();
            Double avgMarks = markRepository.getAVGForTeacher(person.getPersonId());
            result.put(person, avgMarks);
        });
        return result;
    }


    @Override
    public Map<Person, Double> getAVGForStudents() {
        HashMap<Person, Double> result = new HashMap<>();
        List<Person> teachers = personRepository.findAllByType('s');
        teachers.forEach(person -> {
            String name = person.getLastName();
            Double avgMarks = markRepository.getAVGForStudent(person.getPersonId());
            result.put(person, avgMarks);
        });
        return result;
    }
}
