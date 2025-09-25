package co.edu.uptc.temufacebook.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {
    private Long personId;
    private String name;
    private String lastName;
    private int phone;
    private List<EventDTO> events;
    private List<HobieDTO> hobbies;
    private List<PersonDTO> friends;


}
