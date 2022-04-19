package spbstu.deans_office.services.service_impl;

import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.exceptions.ApiRequestException;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.models.Subject;
import spbstu.deans_office.repositories.GroupRepository;
import spbstu.deans_office.repositories.MarkRepository;
import spbstu.deans_office.repositories.PersonRepository;
import spbstu.deans_office.repositories.SubjectRepository;
import spbstu.deans_office.services.MarkService;
import spbstu.deans_office.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import static spbstu.deans_office.utils.Utils.MARK_VALUE_HIGHER_BOUND;
import static spbstu.deans_office.utils.Utils.MARK_VALUE_LOWER_BOUND;
import static spbstu.deans_office.utils.Utils.WRONG_VALUE;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final PersonRepository personRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository, PersonRepository personRepository,
                           SubjectRepository subjectRepository, GroupRepository groupRepository) {
        this.markRepository = markRepository;
        this.personRepository = personRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void updateMark(MarkDTO markNew) {
        Optional<Mark> optionalMark = markRepository.findById(markNew.markId());
        Mark mark = optionalMark.orElseGet(Mark::new);
        Mark result = convertMarkDTOToMark(markNew, mark);
        markRepository.save(result);
    }

    @Override
    public void addMark(MarkDTO markNew) {
        Mark mark = new Mark(null, null, null, markNew.value());
        Mark result = convertMarkDTOToMark(markNew, mark);
        markRepository.save(result);
    }

    private Mark convertMarkDTOToMark(MarkDTO dto, Mark mark) throws ApiRequestException {
        if ((dto.value() < MARK_VALUE_LOWER_BOUND) || (dto.value() > MARK_VALUE_HIGHER_BOUND)) {
            throw new ApiRequestException(WRONG_VALUE + dto.value());
        }
        Subject subject = subjectRepository.findById(dto.subjectId())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_SUBJECT_ID + dto.subjectId()));
        Person student = personRepository.findById(dto.studentId())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_STUDENT_ID + dto.studentId()));
        Person teacher = personRepository.findById(dto.teacherId())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_TEACHER_ID + dto.teacherId()));
        if (!student.getType().equals('s') && !teacher.getType().equals('t')) {
            throw new ApiRequestException(Utils.WRONG_STUDENT_ID + "or" + Utils.WRONG_TEACHER_ID);
        }
        mark.setStudent(student);
        mark.setTeacher(teacher);
        mark.setSubject(subject);
        mark.setValue(dto.value());
        return mark;
    }

    @Override
    public void deleteById(long mark_id) {
        try {
            markRepository.deleteById(mark_id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException(Utils.WRONG_MARK_ID + mark_id);
        }
    }

    @Override
    public List<Mark> getMarksByGroup(String groupName) {
        List<Mark> result = markRepository.findAllByGroupName(groupName);
        if (result.isEmpty()) {
            throw new ApiRequestException(Utils.WRONG_GROUP_NAME + groupName);
        }
        return result;
    }

    @Override
    public List<Mark> getMarksBySubject(String subjectName) {
        List<Mark> result = markRepository.findAllBySubjectName(subjectName);
        if (result.isEmpty()) {
            throw new ApiRequestException(Utils.WRONG_SUBJECT_NAME + subjectName);
        }
        return result;
    }

    @Override
    public List<Mark> getMarksByStudent(long id) {
        List<Mark> result = markRepository.findAllByStudent(id);
        if (result.isEmpty()) {
            throw new ApiRequestException(Utils.WRONG_STUDENT_NAME);
        }
        return result;
    }

    @Override
    public Map<String, Double> getAvgForGroups() {
        Map<String, Double> result = new HashMap<>();
        groupRepository.findAll().forEach(group -> {
            String name = groupRepository.findById(group.getGroupId()).get().getName();
            Double averageMark = markRepository.getAVGForGroup(group.getGroupId());
            result.put(name, averageMark);
        });
        return result;
    }

    @Override
    public Map<String, Double> getAvgForSubjects() {
        Map<String, Double> result = new HashMap<>();
        subjectRepository.findAll().forEach(group -> {
            String name = subjectRepository.findById(group.getSubjectId()).get().getName();
            Double averageMark = markRepository.getAVGForSubject(group.getSubjectId());
            result.put(name, averageMark);
        });
        return result;
    }

    @Override
    public List<Mark> getMarksByTeacher(long id) {
        Optional<Person> teacher = personRepository.findById(id);
        teacher.orElseThrow(() -> new ApiRequestException(Utils.WRONG_TEACHER_ID + id));
        if (!teacher.get().getType().equals('t')) {
            throw new ApiRequestException("Can't find teacher with the name" + id);
        }
        return markRepository.findAllByTeacherId(id);
    }

    @Override
    public Map<String, Double> getMarksAVGByStudentOnAllSubjects(long studentId) throws ApiRequestException {
        Map<String, Double> result = new HashMap<>();
        Person person = personRepository.findById(studentId)
                .orElseThrow(() -> new ApiRequestException("Can't find student with id" + studentId));
        if (person.getType().equals('t')) {
            throw new ApiRequestException("Can't find student with id" + studentId);
        }
        subjectRepository.findAll().forEach(subject -> {
            String subjectName = subject.getName();
            Double avgSubject = markRepository.getAVGByPersonAndSubject(studentId, subject.getSubjectId());
            if (avgSubject != null) {
                result.put(subjectName, avgSubject);
            }
        });
        return result;
    }
}
