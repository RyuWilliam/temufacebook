package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;

import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.PlaceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceMongoRepository extends MongoRepository<PlaceDocument, Long> {

    PlaceDocument findByName(String name);
}
