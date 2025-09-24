package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;

import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.EventEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventPostgresRepository extends CrudRepository<EventEntity, Long> {
    EventEntity findByName(String name);
    List<EventEntity> findByStatus(String status);
}
