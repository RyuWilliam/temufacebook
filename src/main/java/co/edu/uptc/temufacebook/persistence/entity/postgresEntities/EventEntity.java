package co.edu.uptc.temufacebook.persistence.entity.postgresEntities;

import co.edu.uptc.temufacebook.persistence.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime date;

    @ManyToOne
    private PlaceEntity place;

    @Enumerated(EnumType.STRING)
    private Status status;
}