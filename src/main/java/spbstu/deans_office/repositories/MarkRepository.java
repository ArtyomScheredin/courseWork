package spbstu.deans_office.repositories;

import spbstu.deans_office.models.Mark;
import spbstu.deans_office.models.Person;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

    @Query("select m from Mark as m where m.student.group.name=?1")
    List<Mark> findAllByGroupName(String name);

    @Query("select m from Mark as m where m.subject.name=?1")
    List<Mark> findAllBySubjectName(String name);

    @Query("select m from Mark as m where m.student.personId=?1")
    List<Mark> findAllByStudent(long id);

    @Query("select m from Mark as m where m.teacher.personId=?1")
    List<Mark> findAllByTeacherId(long id);

    @Query("select avg(m.value) as second from Mark as m where m.student.group.groupId = ?1")
    Double getAVGForGroup(long id);

    @Query("select avg(m.value) from Mark as m where m.subject.subjectId = ?1")
    Double getAVGForSubject(long id);

    @Query("select avg(m.value) from Mark as m where m.teacher.personId = ?1")
    Double getAVGForTeacher(long id);

    @Query("select avg(m.value) from Mark as m where m.student.personId=?1")
    Double getAVGForStudent(long id);

    @Query("select avg(m.value) from Mark as m where m.student.personId=?1")
    Double getAVGForPerson(long student_id);

    @Query("select p from Mark as m join Person as p on m.student.personId=p.personId group by p.personId order by avg(m.value)")
    List<Person> getStudentOrderedByAVGMark();

    @Query("select p from Mark as m join Person as p on m.student.personId=p.personId group by p.personId having avg(m.value)<?1")
    Iterable<Person> getStudentWithAVGMarkLesserThan(double threshold);

    @Query("select m.student from Mark as m where m.value=?1")
    List<Person> findAllByValue(Integer value);

    @Query("select avg(m.value) from Mark as m where m.student.personId=?1 and m.subject.subjectId=?2 group by m.student")
    Double getAVGByPersonAndSubject(long student_id, long subject_id);

    Set<Mark> findAllByStudent(Person student);

    Set<Mark> findAllByTeacher(Person teacher);
}
