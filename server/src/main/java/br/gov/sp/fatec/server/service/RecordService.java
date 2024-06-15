package br.gov.sp.fatec.server.service;

import br.gov.sp.fatec.server.controller.WebSocketController;
import br.gov.sp.fatec.server.entity.Record;
import br.gov.sp.fatec.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private WebSocketController webSocketController;

    public Record createRecord(Record record) {
        if (record == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record cannot be null");
        }

        if (record.getDateTime() == null) {
            LocalDateTime nowBR = LocalDateTime.now().minusHours(3);
            record.setDateTime(nowBR);
        }

        if (record.getRoom() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room field is required");
        }

        if (record.getCount() == null) {
            record.setCount(0);
        }

        try {
            Record savedRecord = recordRepository.save(record);
            webSocketController.sendRecordUpdate(savedRecord);
            return savedRecord;
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
        return recordRepository.findAll();
    }
    
    public List<Record> getRecordsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return recordRepository.findByDateTimeBetween(startDate, endDate);
    }
}
