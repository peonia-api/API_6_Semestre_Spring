package fatec.sp.gov.login.service;

import com.fasterxml.jackson.annotation.JsonView;
import fatec.sp.gov.login.entity.Area;
import fatec.sp.gov.login.entity.RedZones;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.entity.Views;
import fatec.sp.gov.login.repository.AreaRepository;
import fatec.sp.gov.login.repository.RedZonesRepository;
import fatec.sp.gov.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class RedZonesServices {

    @Autowired
    private RedZonesRepository redRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AreaRepository areaRepo;

    public List<RedZones> findAllRedZones() {
        return redRepo.findAll();
    }

    public List<RedZones> findRedZonesByName(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name");
        }
        return redRepo.findByName(name);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @JsonView(Views.Public.class)
    public RedZones createRedZones(RedZones redZones) {
        if (redZones == null || redZones.getName() == null || redZones.getName().isBlank() ||
                redZones.getDescription() == null || redZones.getDescription().isBlank() ||
                redZones.getCameraSpot() == null || redZones.getCameraSpot().isBlank() ||
                redZones.getPersonLimit() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }

        UUID userId = redZones.getUser().getId();
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        UUID areaId = redZones.getArea().getId();
        Area area = areaRepo.findById(areaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "area not found"));

        redZones.setUser(user);
        redZones.setArea(area);

        return redRepo.save(redZones);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public RedZones findById(UUID id) {
        Optional<RedZones> redZones = redRepo.findById(id);
        if (redZones.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "red zone not found");
        }
        return redZones.get();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public RedZones updateRedZones(UUID id, RedZones redZones) {
        if (redZones == null || redZones.getName() == null || redZones.getName().isBlank() ||
                redZones.getDescription() == null || redZones.getDescription().isBlank() ||
                redZones.getCameraSpot() == null || redZones.getCameraSpot().isBlank() ||
                redZones.getPersonLimit() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
        }

        return redRepo.findById(id).map(existingRedZones -> {
            existingRedZones.setName(redZones.getName());
            existingRedZones.setDescription(redZones.getDescription());
            existingRedZones.setCameraSpot(redZones.getCameraSpot());
            existingRedZones.setPersonLimit(redZones.getPersonLimit());
            existingRedZones.setResponsibleGuard(redZones.getResponsibleGuard());

            if (redZones.getArea() != null) {
                existingRedZones.setArea(redZones.getArea());
            }

            if (redZones.getUser() != null) {
                existingRedZones.setUser(redZones.getUser());
            }

            return redRepo.save(existingRedZones);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Red zone not found"));
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String deleteRedZones(UUID id) {
        if (!redRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "red zone not found");
        }
        redRepo.deleteById(id);
        return "red zone deleted";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @JsonView(Views.Internal.class)
    public Map<String, Object> findRelatedInfoById(UUID id) {
        Optional<RedZones> redZoneOpt = redRepo.findById(id);
        if (redZoneOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Red zone not found");
        }

        RedZones redZone = redZoneOpt.get();
        Map<String, Object> relatedInfo = new HashMap<>();
        relatedInfo.put("redZone", redZone);
        relatedInfo.put("area", redZone.getArea());
        relatedInfo.put("user", redZone.getUser());

        return relatedInfo;
    }



}