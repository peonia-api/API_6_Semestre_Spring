package fatec.sp.gov.login.controller;

import fatec.sp.gov.login.entity.Area;
import fatec.sp.gov.login.entity.Views;
import fatec.sp.gov.login.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<Area> createArea(@RequestBody @JsonView(Views.Internal.class) Area area) {
        Area createdArea = areaService.createArea(area);
        return new ResponseEntity<>(createdArea, HttpStatus.CREATED);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<List<Area>> findAll() {
        List<Area> areas = areaService.findAll();
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<Area> findById(@PathVariable UUID id) {
        Area area = areaService.findById(id);
        return new ResponseEntity<>(area, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<Area> updateArea(@PathVariable UUID id, @RequestBody @JsonView(Views.Internal.class) Area area) {
        Area updatedArea = areaService.updateArea(id, area);
        return new ResponseEntity<>(updatedArea, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<String> deleteArea(@PathVariable UUID id) {
        String response = areaService.deleteArea(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
