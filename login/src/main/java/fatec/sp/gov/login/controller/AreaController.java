package fatec.sp.gov.login.controller;

import fatec.sp.gov.login.entity.Area;
import fatec.sp.gov.login.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/area")
@CrossOrigin
public class AreaController {

    @Autowired
    private AreaService areaService;


    @GetMapping
    public ResponseEntity<List<Area>> getAlAreas() {
        return ResponseEntity.ok(areaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable UUID id) {
        return ResponseEntity.ok(areaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable UUID id, @RequestBody Area area) {
        return ResponseEntity.ok(areaService.updateArea(id, area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArea(@PathVariable UUID id) {
        return ResponseEntity.ok(areaService.deleteArea(id));
    }


}