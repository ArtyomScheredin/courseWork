package spbstu.deans_office.repositories;

import spbstu.deans_office.models.Group;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Query(value = "SELECT * FROM groups LIMIT ?1;", nativeQuery = true)
    List<Group> findAllLimitBy(Integer limit);

    List<Group> findGroupByName(String groupName);
}
