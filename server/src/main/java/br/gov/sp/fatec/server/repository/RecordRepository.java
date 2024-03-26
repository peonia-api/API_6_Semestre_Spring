package br.gov.sp.fatec.server.repository;

import br.gov.sp.fatec.server.entity.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends MongoRepository<Record, String> {

    List<Record> findByOccurrence(Record.OccurrenceType occurrence);

}
