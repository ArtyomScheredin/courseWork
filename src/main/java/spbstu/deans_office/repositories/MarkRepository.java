package spbstu.deans_office.repositories;

import spbstu.deans_office.models.Mark;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

    @Query(value = "SELECT * FROM marks LIMIT ?1", nativeQuery = true)
    List<Mark> findAllLimitBy(int limit);
}
