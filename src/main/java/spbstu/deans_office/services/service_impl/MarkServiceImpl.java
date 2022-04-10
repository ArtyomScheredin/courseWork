package spbstu.deans_office.services.service_impl;

import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.exceptions.ApiRequestException;
import spbstu.deans_office.models.Group;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.models.Subject;
import spbstu.deans_office.repositories.GroupRepository;
import spbstu.deans_office.repositories.MarkRepository;
import spbstu.deans_office.repositories.PersonRepository;
import spbstu.deans_office.repositories.SubjectRepository;
import spbstu.deans_office.services.MarkService;
import spbstu.deans_office.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
    public void updateMark(MarkDTO markDTO) {
        Person student = personRepository.findById(markDTO.student_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_STUDENT_ID_MESSAGE + markDTO.mark_id()));
        if (student.getType() != Person.Type.STUDENT) {
            throw new ApiRequestException(Utils.WRONG_STUDENT_ID_MESSAGE + markDTO.subject_id());
        }

        Person teacher = personRepository.findById(markDTO.teacher_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_TEACHER_ID_MESSAGE + markDTO.teacher_id()));
        if (student.getType() != Person.Type.TEACHER) {
            throw new ApiRequestException(Utils.WRONG_TEACHER_ID_MESSAGE + markDTO.teacher_id());
        }

        Subject subject = subjectRepository.findById(markDTO.subject_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_SUBJECT_ID_MESSAGE + markDTO.subject_id()));

        markRepository.findById(markDTO.mark_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_MARK_ID_MESSAGE + markDTO.mark_id()));

        markRepository.save(new Mark(markDTO.mark_id(), student, subject, teacher, markDTO.value()));
    }

    @Override
    public void addMark(MarkDTO markDTO) {
        Person student = personRepository.findById(markDTO.student_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_STUDENT_ID_MESSAGE + markDTO.student_id()));
        if (student.getType() != Person.Type.STUDENT) {
            throw new ApiRequestException(Utils.WRONG_STUDENT_ID_MESSAGE + markDTO.student_id());
        }

        Person teacher = personRepository.findById(markDTO.teacher_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_TEACHER_ID_MESSAGE + markDTO.teacher_id()));
        if (teacher.getType() != Person.Type.TEACHER) {
            throw new ApiRequestException(Utils.WRONG_TEACHER_ID_MESSAGE + markDTO.teacher_id());
        }

        Subject subject = subjectRepository.findById(markDTO.subject_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_SUBJECT_ID_MESSAGE + markDTO.subject_id()));

        if (markRepository.existsById(markDTO.mark_id())) {
            throw new ApiRequestException(Utils.WRONG_MARK_ID_MESSAGE + markDTO.mark_id());
        }
        markRepository.save(new Mark(student, subject, teacher, markDTO.value()));
    }

    @Override
    public void deleteById(long mark_id) {
        try {
            markRepository.deleteById(mark_id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException(Utils.WRONG_MARK_ID_MESSAGE + mark_id);
        }
    }

    @Override
    public List<Mark> getMarksByGroup(String groupName) {
        List<Mark> result = markRepository.findAllByGroupName(groupName);
        if (result.isEmpty()) {
            throw new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE);
        }
        return result;
    }

    @Override
    public List<Mark> getMarksBySubject(String subjectName) {
        List<Mark> result = markRepository.findAllBySubjectName(subjectName);
        if (result.isEmpty()) {
            throw new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE);
        }
        return result;
    }

    @Override
    public List<Mark> getMarksByStudentSecondName(String studentName) {
        List<Mark> result = markRepository.findAllByStudentName(studentName);
        if (result.isEmpty()) {
            throw new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE);
        }
        return result;
    }

    @Override
    public Map<String, Double> getAvgForGroups() {
        Map<String, Double> result = new HashMap<>();
        groupRepository.findAll().forEach(group -> {
            String name = groupRepository.findById(group.getGroup_id()).get().getName();
            Double averageMark = markRepository.getAVGForGroup(group.getGroup_id());
            result.put(name, averageMark);
        });
        return result;
    }

    @Override
    public Map<String, Double> getAvgForSubjects() {
        Map<String, Double> result = new HashMap<>();
        subjectRepository.findAll().forEach(group -> {
            String name = subjectRepository.findById(group.getSubject_id()).get().getName();
            Double averageMark = markRepository.getAVGForSubject(group.getSubject_id());
            result.put(name, averageMark);
        });
        return result;
    }

    @Override
    public List<Mark> getMarksByTeacher(long id) {
        Optional<Person> teacher = personRepository.findById(id);
        teacher.orElseThrow(() -> new ApiRequestException(Utils.WRONG_TEACHER_ID_MESSAGE + id));
        if (!teacher.get().getType().equals(Person.Type.TEACHER)) {
            throw new ApiRequestException("Can't find teacher with the name" + id);
        }
        return markRepository.findAllByTeacherId(id);
    }

    @Override
    public Map<String, Double> getMarksAVGByStudentOnAllSubjects(long student_id) {
        Map<String, Double> result = new HashMap<>();
        subjectRepository.findAll().forEach(subject -> {
            String subjectName = subject.getName();
            Double avgSubject = markRepository.getAVGByPersonAndSubject(student_id, subject.getSubject_id());
            if (avgSubject != null) {
                result.put(subjectName, avgSubject);
            }});
        return result;
    }
}
