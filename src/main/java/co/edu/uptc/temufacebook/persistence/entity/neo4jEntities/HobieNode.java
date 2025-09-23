package co.edu.uptc.temufacebook.persistence.entity.neo4jEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Getter
@Setter
@Node("Hobie")
public class HobieNode {

    @Id
    @GeneratedValue
    private Long hobieId;

    @Relationship(type = "ASSOCIATED_WITH")
    private List<EventNode> associatedEvents;

    @Relationship(type = "LIKES")
    private List<PersonNode> likes;

}
