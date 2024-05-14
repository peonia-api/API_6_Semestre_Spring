package fatec.sp.gov.login.repository;

import fatec.sp.gov.login.entity.RedZones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RedZonesRepository extends JpaRepository<RedZones, UUID> {
}
