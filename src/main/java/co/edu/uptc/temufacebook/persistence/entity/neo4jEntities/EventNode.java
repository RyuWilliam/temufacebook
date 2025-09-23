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
    private String id;

    private String name;

    private String status;

    @Relationship(type = "ASSOCIATED_WITH")
    private List<EventNode> associatedEvents;
}

