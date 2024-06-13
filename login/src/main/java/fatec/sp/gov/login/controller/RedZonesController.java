package fatec.sp.gov.login.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fatec.sp.gov.login.entity.RedZones;
import fatec.sp.gov.login.entity.Views;
import fatec.sp.gov.login.service.RedZonesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/redZones")
@CrossOrigin
public class RedZonesController {

    @Autowired
    private RedZonesServices service;

    @GetMapping("/searchByName")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<RedZones>> getRedZonesByName(@RequestParam String name) {
        return ResponseEntity.ok(service.findRedZonesByName(name));
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<List<RedZones>> getAllRedZones() {
        return ResponseEntity.ok(service.findAllRedZones());
    }

    @PostMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<RedZones> createRedZones(@RequestBody RedZones redZones) {
        return ResponseEntity.ok(service.createRedZones(redZones));
    }

    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<RedZones> getRedZonesById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<Void> deleteRedZones(@PathVariable UUID id) {
        service.deleteRedZones(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<RedZones> updateRedZones(@PathVariable UUID id, @RequestBody RedZones redZones) {
        return ResponseEntity.ok(service.updateRedZones(id, redZones));
    }

    @GetMapping("/{id}/related-info")
    @JsonView(Views.Internal.class)
    public ResponseEntity<Map<String, Object>> getRelatedInfo(@PathVariable UUID id) {
        Map<String, Object> relatedInfo = service.findRelatedInfoById(id);
        return ResponseEntity.ok(relatedInfo);
    }
}
