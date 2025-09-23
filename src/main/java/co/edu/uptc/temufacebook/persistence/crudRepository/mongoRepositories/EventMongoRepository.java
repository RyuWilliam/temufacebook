package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;


import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventMongoRepository extends MongoRepository<EventDocument, String> {
    List<EventDocument> findByName(String name);

}