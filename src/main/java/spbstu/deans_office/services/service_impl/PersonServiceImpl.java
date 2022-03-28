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

import java.util.List;

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
    public List<Person> getAllLimitBy(Integer limit) {
        return personRepository.findAllLimitBy(limit);
    }

    @Override
    public Double getAvgForPerson(long student_id) {
        if (personRepository.existsById(student_id)) {
            throw new ApiRequestException(Utils.WRONG_STUDENT_ID_MESSAGE + student_id);
        }
        return personRepository.getAVGForPerson(student_id);
    }


    @Override
    public List<Person> getStudentOrderedByAVGMark() {
        return personRepository.getStudentOrderedByAVGMark();
    }

    @Override
    public void deleteIfAVGMarkLessThan(double threshold) {
        if (threshold < 0) {
            throw new ApiRequestException("Incorrect threshold");
        }
        Iterable<Person> people = personRepository.getStudentWithAVGMarkLesserThan(threshold);
        personRepository.deleteAll(people);
    }

    @Override
    public void deletePersonWithMinimalAVGMark() {
        Iterable<Person> people = personRepository.getStudentOrderedByAVGMark();
        if (!people.iterator().hasNext()) {
            throw new ApiRequestException("No people in database!");
        }
        personRepository.delete(people.iterator().next());
    }

    @Override
    public void addPerson(PersonDTO person) {
        if (person.type().equals('s')) {
            Group group = groupRepository.findById(person.group_id())
                    .orElseThrow(() -> new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE + person.group_id()));
            personRepository.save(new Person(person.first_name(),
                    person.last_name(), person.patronymic(), group, person.type()));
        } else {
            personRepository.save(new Person(person.first_name(),
                    person.last_name(), person.patronymic(), null, person.type()));
        }
    }

    @Override
    public void updatePerson(PersonDTO person) {
        if (!personRepository.existsById(person.person_id())) {
            throw new ApiRequestException("WRONG person_id");
        }
        if (person.type().equals('s')) {
            Group group = groupRepository.findById(person.group_id())
                    .orElseThrow(() -> new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE + person.group_id()));
            personRepository.save(new Person(person.person_id(), person.first_name(),
                    person.last_name(), person.patronymic(), group, person.type()));
        } else {
            personRepository.save(new Person(person.person_id(), person.first_name(),
                    person.last_name(), person.patronymic(), null, person.type()));
        }
    }

    @Override
    public List<Person> getPeopleByGroupId(Long group_id) {
        return personRepository.findAllByGroup(group_id);
    }

    @Override
    public List<Person> getPeopleWithMark(Integer value) {
        if ((value < Utils.MARK_VALUE_LOWER_BOUND) || (value > Utils.MARK_VALUE_HIGHER_BOUND)){
                throw new ApiRequestException("WRONG person_id");
        }
        return personRepository.findAllByValue(value);
    }

    @Override
    public List<Utils.Pair<Person, Double>> getAVGForTeachers() {
        return null;//markRepository.getAVGForTeachers();
    }
}
