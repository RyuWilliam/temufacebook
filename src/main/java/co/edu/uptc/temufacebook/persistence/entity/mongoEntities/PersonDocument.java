package co.edu.uptc.temufacebook.persistence.entity.mongoEntities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "persons")
public class PersonDocument {

    @Id
    private Long personId;
    private String name;
    private String lastName;
    private String phone;
    private List<EventDocument> events;
    private List<HobieDocument> hobbies;
    private List<PersonDocument> friends;

}
