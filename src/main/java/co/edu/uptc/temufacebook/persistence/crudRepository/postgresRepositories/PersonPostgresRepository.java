package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;

import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PersonEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PersonPostgresRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findByPersonId(long personId);
    PersonEntity findByName(String name);

    @Modifying
    @Transactional
    void deleteByPersonId(long personId);
}
