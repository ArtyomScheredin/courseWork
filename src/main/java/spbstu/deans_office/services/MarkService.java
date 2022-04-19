package spbstu.deans_office.services;

import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.models.Group;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.models.Subject;
import spbstu.deans_office.utils.Utils;

import java.util.List;
import java.util.Map;

public interface MarkService {

    void updateMark(MarkDTO markDTO);
    void addMark(MarkDTO markDTO);
    void deleteById(long id);

    List<Mark> getMarksByGroup(String groupName);
    List<Mark> getMarksBySubject(String subjectName);
    List<Mark> getMarksByStudent(long id);
    List<Mark> getMarksByTeacher(long id);
    Map<String, Double> getAvgForGroups();
    Map<String, Double> getAvgForSubjects();
    Map<String, Double> getMarksAVGByStudentOnAllSubjects(long student_id);
}
