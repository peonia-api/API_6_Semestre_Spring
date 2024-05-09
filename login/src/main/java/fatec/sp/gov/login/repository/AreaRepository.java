package fatec.sp.gov.login.repository;

import fatec.sp.gov.login.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaRepository extends JpaRepository<Area, UUID> {

}