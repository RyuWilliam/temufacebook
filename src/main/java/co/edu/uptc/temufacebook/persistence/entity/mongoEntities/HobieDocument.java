package co.edu.uptc.temufacebook.persistence.entity.mongoEntities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Document(collection = "hobbies")
public class HobieDocument {

    @Id
    private Long hobieId;
    private String name;
    private String description;
    private List<EventDocument> relatedEvents;
}
