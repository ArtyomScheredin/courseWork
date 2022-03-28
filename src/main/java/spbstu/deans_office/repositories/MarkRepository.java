package spbstu.deans_office.repositories;

import antlr.debug.ParserEventSupport;
import spbstu.deans_office.models.Group;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.models.Subject;
import spbstu.deans_office.utils.Utils;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

    @Query(value = "select Mark from Mark where Mark.student.group_id.name=?1")
    List<Mark> findAllByGroupName(String name);

    @Query(value = "select Mark from Mark where Mark.subject.name=?1")
    List<Mark> findAllBySubjectName(String name);

    @Query(value = "select Mark from Mark where Mark.student.last_name=?1")
    List<Mark> findAllByStudentName(String lastName);

    @Query(value = "SELECT * FROM marks LIMIT ?1", nativeQuery = true)
    List<Mark> findAllLimitBy(int limit);

    /*@Query(value = "select Mark.student.group_id, avg(Mark.value) from Mark group by Mark.student.group_id")
    List<Utils.Pair<Group, Double>> getAVGForGroups();

    @Query(value = "select Mark.subject, avg(Mark.value) from Mark group by Mark.student.group_id")
    List<Utils.Pair<Subject, Double>> getAVGForSubjects();

    @Query(value = "select Mark.teacher, avg(Mark.value) from Mark group by Mark.teacher")
    List<Utils.Pair<Person, Double>> getAVGForTeachers();*/
}
