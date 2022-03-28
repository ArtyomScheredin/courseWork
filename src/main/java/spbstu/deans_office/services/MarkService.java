package spbstu.deans_office.services;

import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.models.Group;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.models.Subject;
import spbstu.deans_office.utils.Utils;

import java.util.List;

public interface MarkService {
    List<Mark> getAllLimitBy(Integer limit);

    void updateMark(MarkDTO markDTO);
    void addMark(MarkDTO markDTO);
    void deleteById(long id);

    List<Mark> getMarksByGroup(String groupName);
    List<Mark> getMarksBySubject(String subjectName );
    List<Mark> getMarksByStudent(String studentName);
    List<Utils.Pair<Group, Double>> getAvgForGroups();
    List<Utils.Pair<Subject, Double>> getAvgForSubjects();
}
