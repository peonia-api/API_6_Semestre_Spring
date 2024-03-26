package br.gov.sp.fatec.server.service;

import br.gov.sp.fatec.server.entity.Record;
import br.gov.sp.fatec.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    public List<Record> listRecordsByOccurrence(Record.OccurrenceType occurrence) {
        return recordRepository.findByOccurrence(occurrence);
    }
}
