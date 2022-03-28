package spbstu.deans_office.services.service_impl;

import spbstu.deans_office.models.Subject;
import spbstu.deans_office.repositories.SubjectRepository;
import spbstu.deans_office.services.SubjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllLimitBy(Integer limit) {
        return subjectRepository.findAllLimitBy(limit);
    }
}
