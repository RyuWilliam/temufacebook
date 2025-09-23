package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;


import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByName(String name);
}

