package co.edu.uptc.temufacebook.persistence.entity.postgresEntities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class HobieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobieId;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "Event_hobie")
    private List<EventEntity> relatedEvents;
}
