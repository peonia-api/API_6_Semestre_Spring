package br.gov.sp.fatec.server.service;

import br.gov.sp.fatec.server.entity.Record;
import br.gov.sp.fatec.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record createRecord(Record record) {
        if (record == null
                || record.getDate() == null
                || record.getHour() == null
                || record.getRoom() == null
                || record.getOccurrence() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid record");
        }
        return recordRepository.save(record);
    }

    public List<Record> listRecordsByOccurrence(Record.OccurrenceType occurrence) {
        return recordRepository.findByOccurrence(occurrence);
    }

    public List<Record> listAllRecords() {
        List<Record> records = recordRepository.findAll();
        return records;
    }
}
