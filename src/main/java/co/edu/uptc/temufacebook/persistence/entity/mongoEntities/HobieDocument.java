package co.edu.uptc.temufacebook.persistence.entity.mongoEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "hobbies")
public class HobieDocument {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> eventIds;
}
