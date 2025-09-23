package co.edu.uptc.temufacebook.persistence.entity.mongoEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "places")
public class PlaceDocument {

    @Id
    private Long placeId;
    private String name;
    private String address;
}
