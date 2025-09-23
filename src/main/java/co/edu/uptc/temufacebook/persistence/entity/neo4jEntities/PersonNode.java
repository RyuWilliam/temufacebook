package co.edu.uptc.temufacebook.persistence.entity.neo4jEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;


@Node("Person")
public class PersonNode {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;
    private String phone;

    @Relationship(type = "FRIENDS_WITH")
    private List<PersonNode> friends;

    @Relationship(type = "PARTICIPATES_IN")
    private List<EventNode> events;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<PersonNode> getFriends() {
        return friends;
    }

    public void setFriends(List<PersonNode> friends) {
        this.friends = friends;
    }

    public List<EventNode> getEvents() {
        return events;
    }

    public void setEvents(List<EventNode> events) {
        this.events = events;
    }
}
