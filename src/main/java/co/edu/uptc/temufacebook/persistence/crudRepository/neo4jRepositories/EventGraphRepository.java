package co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories;

import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface EventGraphRepository extends Neo4jRepository<EventNode, Long> {
    List<EventNode> findByName(String name);
}
