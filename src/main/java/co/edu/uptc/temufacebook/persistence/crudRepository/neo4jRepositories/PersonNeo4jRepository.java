package co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories;

import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.PersonNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonNeo4jRepository extends Neo4jRepository<PersonNode, Long> {

    PersonNode findByPersonId(Long personId);
    List<PersonNode> findFriendsByPersonId(Long personId);

    @Query("MATCH (p1:PersonNode) WHERE ID(p1) = $personNodeId " +
           "MATCH (p2:PersonNode) WHERE ID(p2) = $friendNodeId " +
           "MERGE (p1)-[:FRIENDS]->(p2) " +
           "MERGE (p2)-[:FRIENDS]->(p1)")
    void createFriendRelation(@Param("personNodeId") Long personNodeId, @Param("friendNodeId") Long friendNodeId);
    
    @Query("MATCH (p1:PersonNode {personId: $personId})-[:FRIENDS]-(p2:PersonNode {personId: $friendId}) " +
           "RETURN COUNT(*) > 0")
    boolean areFriends(@Param("personId") Long personId, @Param("friendId") Long friendId);
    
    @Query("MATCH (p1:PersonNode {personId: $personId})-[r:FRIENDS]-(p2:PersonNode {personId: $friendId}) " +
           "DELETE r")
    void removeFriendRelation(@Param("personId") Long personId, @Param("friendId") Long friendId);
}
