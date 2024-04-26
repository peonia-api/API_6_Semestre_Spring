package br.gov.sp.fatec.server.controller;

import br.gov.sp.fatec.server.entity.Record;
import br.gov.sp.fatec.server.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/record")
@CrossOrigin
public class RecordController {
    @Autowired
    private RecordService service;

    @GetMapping
    public ResponseEntity<List<Record>> listRecordsByOccurrence(@RequestParam("occurrence") Record.OccurrenceType occurrence) {
        List<Record> records = service.listRecordsByOccurrence(occurrence);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Record>> listAllRecords() {
        List<Record> records = service.listAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Record record) {
        Record createdRecord = service.createRecord(record);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }
}