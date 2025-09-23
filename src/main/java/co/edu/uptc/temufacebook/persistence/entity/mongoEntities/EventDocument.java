package co.edu.uptc.temufacebook.persistence.entity.mongoEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.uptc.temufacebook.persistence.Status;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "events")
public class EventDocument {

    @Id
    private Long eventId;
    private String name;
    private LocalDateTime dateEvent;
    private PlaceDocument place;
    private List<HobieDocument> hobbies;
    private List<PersonDocument> participants;
    private List<EventDocument> associatedEvents;
    private Status status;
}
