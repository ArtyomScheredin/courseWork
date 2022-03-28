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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final PersonRepository personRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository, PersonRepository personRepository, SubjectRepository subjectRepository, GroupRepository groupRepository) {
        this.markRepository = markRepository;
        this.personRepository = personRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Mark> getAllLimitBy(Integer limit) {
        return markRepository.findAllLimitBy(limit);
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
        if (student.getType() != Person.Type.TEACHER) {
            throw new ApiRequestException(Utils.WRONG_TEACHER_ID_MESSAGE + markDTO.teacher_id());
        }

        Subject subject = subjectRepository.findById(markDTO.subject_id())
                .orElseThrow(() -> new ApiRequestException(Utils.WRONG_SUBJECT_ID_MESSAGE + markDTO.subject_id()));

        if (markRepository.existsById(markDTO.mark_id())) {
            throw new ApiRequestException(Utils.WRONG_MARK_ID_MESSAGE + markDTO.mark_id());
        }
        markRepository.save(new Mark(markDTO.mark_id(), student, subject, teacher, markDTO.value()));
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
    public List<Mark> getMarksByStudent(String studentName) {
        List<Mark> result = markRepository.findAllByStudentName(studentName);
        if (result.isEmpty()) {
            throw new ApiRequestException(Utils.WRONG_GROUP_ID_MESSAGE);
        }
        return result;
    }

    @Override
    public List<Utils.Pair<Group, Double>> getAvgForGroups() {
     /*   List<Utils.Pair<Group, Double>> result = markRepository.getAVGForGroups();
        return result;*/
        return null;
    }

    @Override
    public List<Utils.Pair<Subject, Double>> getAvgForSubjects() {
        /*List<Utils.Pair<Subject, Double>> result = markRepository.getAVGForSubjects();
        return result;*/
        return null;
    }
}
