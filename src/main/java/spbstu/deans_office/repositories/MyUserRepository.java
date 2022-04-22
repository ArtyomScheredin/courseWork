package spbstu.deans_office.repositories;

import spbstu.deans_office.models.MyUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findUserByUserName(String userName);
}
