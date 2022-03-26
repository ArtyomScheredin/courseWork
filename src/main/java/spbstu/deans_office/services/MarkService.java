package spbstu.deans_office.services;

import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.exceptions.ApiRequestException;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.models.Subject;
import spbstu.deans_office.repositories.MarkRepository;
import spbstu.deans_office.repositories.PersonRepository;
import spbstu.deans_office.repositories.SubjectRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final PersonRepository personRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public MarkService(MarkRepository markRepository, PersonRepository personRepository, SubjectRepository subjectRepository) {
        this.markRepository = markRepository;
        this.personRepository = personRepository;
        this.subjectRepository = subjectRepository;
    }

    List<Mark> getAll(Integer limit) {
        return markRepository.findAllLimitBy(limit);
    }

    void updateMark(MarkDTO markDTO) {
        final String wrongStudentIdMessage = "WRONG student id. Can't find student with id " + markDTO.student_id();
        final String wrongTeacherIdMessage = "WRONG teacher id. Can't find teacher with id " + markDTO.teacher_id();
        final String wrongSubjectIdMessage = "WRONG subject id. Can't find subject with id " + markDTO.teacher_id();
        final String wrongMarkIdMessage = "WRONG subject id. Can't find subject with id " + markDTO.mark_id();
        Person student = personRepository.findById(markDTO.student_id())
                .orElseThrow(() -> new ApiRequestException(wrongStudentIdMessage));
        if (student.getType() != Person.Type.STUDENT) {
            throw new ApiRequestException(wrongStudentIdMessage);
        }
        Person teacher = personRepository.findById(markDTO.teacher_id())
                .orElseThrow(() -> new ApiRequestException(wrongTeacherIdMessage));
        if (student.getType() != Person.Type.TEACHER) {
            throw new ApiRequestException(wrongTeacherIdMessage);
        }
        Subject subject = subjectRepository.findById(markDTO.subject_id())
                .orElseThrow(() -> new ApiRequestException(wrongSubjectIdMessage));

        markRepository.findById(markDTO.mark_id()).orElseThrow(() -> new ApiRequestException(wrongMarkIdMessage));

        markRepository.save(new Mark(markDTO.mark_id(), student, subject, teacher, markDTO.value()));
    }

    void addMark(MarkDTO markDTO) {
        final String wrongStudentIdMessage = "WRONG student id. Can't find student with id " + markDTO.student_id();
        final String wrongTeacherIdMessage = "WRONG teacher id. Can't find teacher with id " + markDTO.teacher_id();
        final String wrongSubjectIdMessage = "WRONG subject id. Can't find subject with id " + markDTO.teacher_id();
        final String wrongMarkIdMessage = "WRONG subject id. Can't find subject with id " + markDTO.mark_id();
        Person student = personRepository.findById(markDTO.student_id())
                .orElseThrow(() -> new ApiRequestException(wrongStudentIdMessage));
        if (student.getType() != Person.Type.STUDENT) {
            throw new ApiRequestException(wrongStudentIdMessage);
        }
        Person teacher = personRepository.findById(markDTO.teacher_id())
                .orElseThrow(() -> new ApiRequestException(wrongTeacherIdMessage));
        if (student.getType() != Person.Type.TEACHER) {
            throw new ApiRequestException(wrongTeacherIdMessage);
        }
        Subject subject = subjectRepository.findById(markDTO.subject_id())
                .orElseThrow(() -> new ApiRequestException(wrongSubjectIdMessage));

        if (markRepository.existsById(markDTO.mark_id())) {
            throw new ApiRequestException(wrongMarkIdMessage);
        }

        markRepository.save(new Mark(markDTO.mark_id(), student, subject, teacher, markDTO.value()));
    }

    void deleteById(Long id) {
        try {
            markRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
