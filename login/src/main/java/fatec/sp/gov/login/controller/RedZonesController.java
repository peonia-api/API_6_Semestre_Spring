package fatec.sp.gov.login.controller;

import fatec.sp.gov.login.entity.RedZones;
import fatec.sp.gov.login.service.RedZonesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/redZones")
@CrossOrigin
public class RedZonesController {

    @Autowired
    private RedZonesServices service;

    @GetMapping
    public ResponseEntity<List<RedZones>> getAllRedZones() {
        return ResponseEntity.ok(service.findAllRedZones());
    }

    @PostMapping
    public ResponseEntity<RedZones> createRedZones(@RequestBody RedZones redZones) {
        return ResponseEntity.ok(service.createRedZones(redZones));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RedZones> getRedZonesById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRedZones(@PathVariable UUID id) {
        service.deleteRedZones(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RedZones> updateRedZones(@PathVariable UUID id, @RequestBody RedZones redZones) {
        return ResponseEntity.ok(service.updateRedZones(id, redZones));
    }

}
