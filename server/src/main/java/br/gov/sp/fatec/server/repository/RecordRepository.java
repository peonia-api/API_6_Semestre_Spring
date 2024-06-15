package br.gov.sp.fatec.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import br.gov.sp.fatec.server.entity.Record;

public interface RecordRepository extends MongoRepository<Record, String> {
    List<Record> findByOccurrence(Record.OccurrenceType occurrence);
    @Query("{ 'dateTime' : { $gte: ?0, $lte: ?1 } }")
    List<Record> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}


