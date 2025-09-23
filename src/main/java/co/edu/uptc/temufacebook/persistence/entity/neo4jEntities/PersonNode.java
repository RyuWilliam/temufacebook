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
    private Long personId;

    @Relationship(type = "FRIENDS_WITH")
    private List<PersonNode> friends;
}
