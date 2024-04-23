package br.gov.sp.fatec.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import br.gov.sp.fatec.login.entity.Record;

public interface RecordRepository extends MongoRepository<Record, String> {
    List<Record> findByOccurrence(Record.OccurrenceType occurrence);
}
