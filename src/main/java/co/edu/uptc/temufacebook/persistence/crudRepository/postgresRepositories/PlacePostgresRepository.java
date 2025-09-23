package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;

import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PlaceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlacePostgresRepository extends CrudRepository<PlaceEntity, Long> {
    PlaceEntity findByName(String name);
}
