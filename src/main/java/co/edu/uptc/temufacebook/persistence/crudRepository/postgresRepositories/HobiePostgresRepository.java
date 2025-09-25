package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;

import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.HobieEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface HobiePostgresRepository extends CrudRepository<HobieEntity, Long> {

    HobieEntity findByName(String name);
    HobieEntity findByHobieId(Long hobieId);

    @Modifying
    @Transactional
    void deleteByHobieId(Long hobieId);
}
