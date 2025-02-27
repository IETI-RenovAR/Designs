package org.adaschool.project.repository;

import org.adaschool.project.model.Quotation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends MongoRepository<Quotation, String> {
}
