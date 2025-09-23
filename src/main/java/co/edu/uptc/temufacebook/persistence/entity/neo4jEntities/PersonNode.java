package co.edu.uptc.temufacebook.persistence.entity.neo4jEntities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Getter
@Setter
@Node("Person")
public class PersonNode {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;

    @Relationship(type = "FRIEND_WITH")
    private List<PersonNode> friends;

    @Relationship(type = "PARTICIPATES_IN")
    private List<EventNode> events;
}
