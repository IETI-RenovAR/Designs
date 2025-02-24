package edu.escuelaing.IETI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository extends MongoRepository<Product, String> {
}
