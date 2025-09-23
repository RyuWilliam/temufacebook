package co.edu.uptc.temufacebook.persistence.entity.postgresEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
