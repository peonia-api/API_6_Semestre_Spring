package fatec.sp.gov.login.controller;

import fatec.sp.gov.login.client.via.record.Register;
import fatec.sp.gov.login.client.via.record.Viarecord;
import fatec.sp.gov.login.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/records")
public class RecordController {
    @Autowired
    private Viarecord viarecord;


    @GetMapping
    public ResponseEntity<List<Register>> getAllRecords(@RequestParam(required = false) String occurrence) {
        List<Register> records;
        if (occurrence != null) {
            records = viarecord.getRecords(occurrence);
        } else {
            records = viarecord.getRecords(null);
        }
        return ResponseEntity.ok(records);
    }
}