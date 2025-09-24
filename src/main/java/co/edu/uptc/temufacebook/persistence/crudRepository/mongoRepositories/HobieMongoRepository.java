package co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories;

import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.HobieDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HobieMongoRepository extends MongoRepository<HobieDocument, Long> {
    HobieDocument findByName(String name);
}
