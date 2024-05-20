package fatec.sp.gov.login.repository;

import fatec.sp.gov.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> searchByName(String nameUser);

    @Query("select u from User u where u.name = ?1")
    Optional<User> findByName(String nameUser);

    Optional<User> searchByEmail(String email);

    List<User> findByNameContains(String nameUser);

    @Query("select u from User u where u.name like %?1%")
    List<User> searchByContainedName(String nameUser);

    Optional<User> findByNameAndPassword(String nameUser, String password);

    @Query("select u from User u where u.name = ?1 and u.password = ?2")
    Optional<User> searchByNameAndPassword(String nameUser, String password);




}
