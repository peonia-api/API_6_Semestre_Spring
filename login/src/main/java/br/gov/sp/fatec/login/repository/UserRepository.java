package br.gov.sp.fatec.login.repository;

import br.gov.sp.fatec.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}