package fatec.sp.gov.login.service;

import fatec.sp.gov.login.entity.RedZones;
import fatec.sp.gov.login.repository.RedZonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RedZonesServices {

    @Autowired
    private RedZonesRepository redRepo;

    public List<RedZones> findAllRedZones() {
        return redRepo.findAll();
    }

    public RedZones createRedZones(RedZones redZones) {
        if (redZones == null || redZones.getName() == null || redZones.getName().isBlank() ||
                redZones.getDescription() == null || redZones.getDescription().isBlank() ||
                redZones.getCameraSpot() == null || redZones.getCameraSpot().isBlank() ||
                redZones.getPersonLimit() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }
        return redRepo.save(redZones);
    }

    public RedZones findById(UUID id) {
        Optional<RedZones> redZones = redRepo.findById(id);
        if (redZones.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "red zone not found");
        }
        return redZones.get();
    }

    public RedZones updateRedZones(UUID id, RedZones redZones) {
        if (redZones == null || redZones.getName() == null || redZones.getName().isBlank() ||
                redZones.getDescription() == null || redZones.getDescription().isBlank() ||
                redZones.getCameraSpot() == null || redZones.getCameraSpot().isBlank() ||
                redZones.getPersonLimit() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }
        return redRepo.findById(id).map(existingRedZones -> {
            existingRedZones.setName(redZones.getName());
            existingRedZones.setDescription(redZones.getDescription());
            existingRedZones.setCameraSpot(redZones.getCameraSpot());
            existingRedZones.setPersonLimit(redZones.getPersonLimit());
            return redRepo.save(existingRedZones);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "red zone not found"));
    }

    public String deleteRedZones(UUID id) {
        if (!redRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "red zone not found");
        }
        redRepo.deleteById(id);
        return "red zone deleted";
    }


}