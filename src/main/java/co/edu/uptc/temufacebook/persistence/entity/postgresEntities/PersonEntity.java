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
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    private String name;
    private String lastName;
    private int phone;

    @ManyToMany
    @JoinTable(name = "Person_event")
    private List<EventEntity> events;

    @ManyToMany
    @JoinTable(name = "Person_hobie")
    private List<HobieEntity> hobbies;

}
