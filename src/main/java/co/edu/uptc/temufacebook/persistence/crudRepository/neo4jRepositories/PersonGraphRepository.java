package co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.PersonNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PersonGraphRepository extends Neo4jRepository<PersonNode, Long> {
    List<PersonNode> findByName(String name);
}
