package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;

import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonPostgresRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findByName(String name);
}
