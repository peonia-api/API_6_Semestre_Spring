package fatec.sp.gov.login.service;

import fatec.sp.gov.login.entity.Area;
import fatec.sp.gov.login.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepo;


    public Area createArea(Area area) {
        if (area == null || area.getName() == null || area.getName().isBlank() ||
        area.getDescription() == null || area.getDescription().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }
        return areaRepo.save(area);
    }

    public List<Area> findAll() {
        return areaRepo.findAll();
    }

    public Area findById(UUID id) {
        Optional<Area> area = areaRepo.findById(id);
        if (area.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "area not found");
        }
        return area.get();
    }

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

    public String deleteArea (UUID id) {
        if (!areaRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "area not found");
        }
        areaRepo.deleteById(id);
        return "area deleted";
    }


}
