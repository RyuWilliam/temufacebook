package co.edu.uptc.temufacebook.persistence.entity.neo4jEntities;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;


@Node("Event")
public class EventNode {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "ASSOCIATED_WITH")
    private List<EventNode> associatedEvents;

    @Relationship(type = "PARTICIPATES_IN", direction = Relationship.Direction.INCOMING)
    private List<PersonNode> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventNode> getAssociatedEvents() {
        return associatedEvents;
    }

    public void setAssociatedEvents(List<EventNode> associatedEvents) {
        this.associatedEvents = associatedEvents;
    }

    public List<PersonNode> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PersonNode> participants) {
        this.participants = participants;
    }
}

