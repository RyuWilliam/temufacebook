package co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories;

import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface EventNeo4jRepository extends Neo4jRepository<EventNode, Long> {

    EventNode findByEventId(long eventId);
    List<EventNode> findAssociatedEventsByEventId(long eventId);
}
