package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;

import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.HobieEntity;
import org.springframework.data.repository.CrudRepository;

public interface HobiePostgresRepository extends CrudRepository<HobieEntity, Long> {

    HobieEntity findByName(String name);
}
