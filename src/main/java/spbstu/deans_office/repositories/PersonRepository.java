package spbstu.deans_office.repositories;

import spbstu.deans_office.models.Person;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query(value = "SELECT * FROM people LIMIT ?1", nativeQuery = true)
    List<Person> findAllLimitBy(int limit);

    @Query(value = "select avg(Mark.value) from Mark where Mark.student.person_id=?1")
    Double getAVGForPerson(long student_id);

    @Query(value = "select Mark.student from Mark group by Mark.student order by avg(value)")
    List<Person> getStudentOrderedByAVGMark();

    @Query(value = "select Mark.student from Mark group by Mark.student having avg(Mark.value) < ?1")
    Iterable<Person> getStudentWithAVGMarkLesserThan(double threshold);

    @Query(value = "select Mark.student from Mark where Mark.value.name=?1")
    List<Person> findAllByValue(Integer value);

    @Query(value = "select Person from Person where Person.group.group_id=?1")
    List<Person> findAllByGroup(long group_id);
}
