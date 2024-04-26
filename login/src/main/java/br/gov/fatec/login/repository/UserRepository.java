package br.gov.fatec.login.repository;


import br.gov.fatec.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}