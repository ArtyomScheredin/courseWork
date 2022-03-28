package spbstu.deans_office.services;

import spbstu.deans_office.models.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllLimitBy(Integer limit);
}
