package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;

import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.PersonDocument;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PersonMongoRepository extends MongoRepository<PersonDocument, Long> {

    PersonDocument findByName(String name);

    @Query("{}")
    List<PersonDocument> getAllPersons();
}
