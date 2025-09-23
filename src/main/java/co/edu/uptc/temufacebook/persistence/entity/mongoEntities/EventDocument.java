package co.edu.uptc.temufacebook.persistence.entity.mongoEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "events")
public class EventDocument {
    @Id
    private String id;
    private String name;
    private LocalDateTime date;
    private PlaceDocument place;
    private List<String> hobieIds;
    private String status;
    private List<String> participantIds;
    private List<String> associatedEventIds;
}
