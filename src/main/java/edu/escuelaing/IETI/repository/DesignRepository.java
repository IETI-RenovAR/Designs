package edu.escuelaing.IETI.repository;

import edu.escuelaing.IETI.model.Design;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignRepository extends MongoRepository<Design, String> {
    List<Design> findByIsPublic(boolean isPublic);
    List<Design> findByUserId(String userId);
}
