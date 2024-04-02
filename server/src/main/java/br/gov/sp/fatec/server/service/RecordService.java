package br.gov.sp.fatec.server.service;

import br.gov.sp.fatec.server.entity.Record;
import br.gov.sp.fatec.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record createRecord(Record record) {
        if (record == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record cannot be null");
        }
        if (record.getDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date field is required");
        }
        if (record.getHour() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hour field is required");
        }
        if (record.getRoom() == null)  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room field is required");
        }


        try {
            return recordRepository.save(record);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data integrity violation occurred", e);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", e);
        }

    }

    public List<Record> listRecordsByOccurrence(Record.OccurrenceType occurrence) {
        return recordRepository.findByOccurrence(occurrence);
    }

    public List<Record> listAllRecords() {
        List<Record> records = recordRepository.findAll();
        return records;
    }
}
