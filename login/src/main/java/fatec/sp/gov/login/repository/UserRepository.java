package fatec.sp.gov.login.repository;

import fatec.sp.gov.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> searchByName(String nameUser);

    @Query("select u from User u where u.name = ?1")
    public Optional<User> findByName(String nameUser);

    public Optional<User> searchByEmail(String email);

    public List<User> findByNameContains(String nameUser);

    @Query("select u from User u where u.name like %?1%")
    public List<User> searchByContainedName(String nameUser);

    public Optional<User> findByNameAndPassword(String nameUser, String password);

    @Query("select u from User u where u.name = ?1 and u.password = ?2")
    public Optional<User> searchByNameAndPassword(String nameUser, String password);

    public List<User> findByAuthorizationsName(String nameAuthorization);

    @Query("select u from User u join u.authorizations a where a.name = ?1")
    public List<User> searchByNameAuthorization(String nameAuthorization);

    public List<User> findByIdGreaterThan(UUID id);

    @Query("select u from User u where u.id > ?1")
    public List<User> searchByLargestId(UUID id);
}
