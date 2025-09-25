package co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories;

import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.EventEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventPostgresRepository extends CrudRepository<EventEntity, Long> {
    EventEntity findByName(String name);
    List<EventEntity> findByStatus(String status);
    EventEntity findByEventId(long eventId);

    @Modifying
    @Transactional
    void deleteByEventId(long eventId);
}
