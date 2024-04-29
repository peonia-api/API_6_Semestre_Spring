package fatec.sp.gov.login.repository;

import fatec.sp.gov.login.entity.Authorization;
import fatec.sp.gov.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorizationRepository extends JpaRepository<Authorization,Long> {

    public Authorization findByName(String name);
}
