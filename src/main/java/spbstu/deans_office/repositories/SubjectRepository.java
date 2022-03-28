package spbstu.deans_office.repositories;

import spbstu.deans_office.models.Subject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    @Query(value = "SELECT * FROM subjects LIMIT ?1;", nativeQuery = true)
    List<Subject> findAllLimitBy(Integer limit);
}
