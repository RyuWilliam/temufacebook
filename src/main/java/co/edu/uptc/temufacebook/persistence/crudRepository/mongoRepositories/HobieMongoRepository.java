package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;

import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.HobieDocument;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface HobieMongoRepository extends MongoRepository<HobieDocument, Long> {

    HobieDocument findByName(String name);

    @Query("{}")
    List<HobieDocument> getAllHobbies();
    
}
