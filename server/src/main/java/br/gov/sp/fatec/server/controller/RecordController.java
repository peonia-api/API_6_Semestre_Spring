package br.gov.sp.fatec.server.controller;

import br.gov.sp.fatec.server.entity.Record;
import br.gov.sp.fatec.server.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/record")
@CrossOrigin

public class RecordController {
    @Autowired
    private RecordService service;

    @GetMapping
    public List<Record> listRecordsByOccurrence(Record.OccurrenceType occurrence) {
        return service.listRecordsByOccurrence(occurrence);
    }

    @GetMapping("/all")
    public List<Record> listAllRecords() {
        return service.listAllRecords();
    }

    @PostMapping
    public Record createRecord(@RequestBody Record record) {
        return service.createRecord(record);
    }

}