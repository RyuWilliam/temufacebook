package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;

import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventMongoRepository extends MongoRepository<EventDocument, Long> {

    EventDocument findByName(String name);

    @Query("{}")
    List<EventDocument> getAllEvents();

    @Query("{ 'status': ?0 }")
    List<EventDocument> findByStatus(String status);
}
