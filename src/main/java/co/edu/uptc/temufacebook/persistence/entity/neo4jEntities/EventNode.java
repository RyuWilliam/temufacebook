package co.edu.uptc.temufacebook.persistence.entity.neo4jEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Getter
@Setter
@Node("Event")
public class EventNode {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "ASSOCIATED_WITH")
    private List<EventNode> associatedEvents;

    @Relationship(type = "PARTICIPATES_IN", direction = Relationship.Direction.INCOMING)
    private List<PersonNode> participants;

    @Relationship(type = "IS_OF")
    private List<HobieNode> hobbies;

}

