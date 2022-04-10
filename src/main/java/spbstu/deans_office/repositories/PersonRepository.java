package spbstu.deans_office.repositories;

import spbstu.deans_office.models.Person;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person as p where p.group.group_id=?1")
    List<Person> findAllByGroup(long group_id);

    List<Person> findAllByType(Character type);
}
