package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;


import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.HobieDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HobieMongoRepository extends MongoRepository<HobieDocument, String> {
    List<HobieDocument> findByName(String name);
}