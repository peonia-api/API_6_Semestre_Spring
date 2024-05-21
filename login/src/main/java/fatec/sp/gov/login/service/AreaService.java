package fatec.sp.gov.login.service;

import fatec.sp.gov.login.entity.Area;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.repository.AreaRepository;
import fatec.sp.gov.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepo;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public Area createArea(Area area) {
        if (area == null || area.getName() == null || area.getName().isBlank() ||
                area.getDescription() == null || area.getDescription().isBlank() ||
                area.getUser() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }

        UUID userId = area.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        area.setUser(user);
        return areaRepo.save(area);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Area> findAll() {
        return areaRepo.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Area findById(UUID id) {
        Optional<Area> area = areaRepo.findById(id);
        if (area.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "area not found");
        }
        return area.get();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Area updateArea (UUID id, Area area) {
        if (area == null || area.getName() == null || area.getName().isBlank() ||
                area.getDescription() == null || area.getDescription().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }
        return areaRepo.findById(id).map(existingUser -> {
            existingUser.setName(area.getName());
            existingUser.setDescription(area.getDescription());

            return areaRepo.save(existingUser);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));


    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteArea (UUID id) {
        if (!areaRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "area not found");
        }
        areaRepo.deleteById(id);
        return "area deleted";
    }


}