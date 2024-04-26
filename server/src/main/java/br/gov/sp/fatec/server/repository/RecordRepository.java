package br.gov.sp.fatec.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import br.gov.sp.fatec.server.entity.Record;

public interface RecordRepository extends MongoRepository<Record, String> {
    List<Record> findByOccurrence(Record.OccurrenceType occurrence);
}
