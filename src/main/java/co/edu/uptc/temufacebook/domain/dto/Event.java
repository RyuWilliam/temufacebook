package co.edu.uptc.temufacebook.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class Event {
    private String eventId;
    private String name;
    private LocalDateTime date;
    private Place place;
    private List<Hobie> hobieList;
    private Status status;
    private List<Person> participants;
    private List<Event> associatedEvents;

}
