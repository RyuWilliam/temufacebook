package co.edu.uptc.temufacebook.domain.dto;

import co.edu.uptc.temufacebook.persistence.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventDTO {
    private Long eventId;
    private String name;
    private LocalDateTime dateEvent;
    private PlaceDTO place;
    private List<HobieDTO> hobbies;
    private Status status;
    private List<PersonDTO> participants;
    private List<EventDTO> events;

}
