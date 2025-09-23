package co.edu.uptc.temufacebook.persistence.entity.postgresEntities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private String name;
    private String address;

    @OneToMany(mappedBy = "place")
    private List<EventEntity> events;

}