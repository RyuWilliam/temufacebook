package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;

import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.PlaceDocument;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PlaceMongoRepository extends MongoRepository<PlaceDocument, Long> {

    PlaceDocument findByName(String name);

    @Query("{}")
    List<PlaceDocument> getAllPlaces();
}
