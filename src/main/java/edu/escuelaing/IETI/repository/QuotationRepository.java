package edu.escuelaing.IETI.repository;

import edu.escuelaing.IETI.model.Quotation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends MongoRepository<Quotation, String> {
}
