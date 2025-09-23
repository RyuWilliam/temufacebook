package co.edu.uptc.temufacebook.persistence.entity.postgresEntities;

import co.edu.uptc.temufacebook.persistence.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String name;
    private LocalDateTime dateEvent;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "Event_Hobie")
    private List<HobieEntity> hobbies;

    @ManyToMany
    @JoinTable(name = "Event_Person")
    private List<PersonEntity> participants;

}