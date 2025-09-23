package co.edu.uptc.temufacebook.domain.dto;

import co.edu.uptc.temufacebook.persistence.Status;

import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {
    private Long eventId;
    private String name;
    private LocalDateTime date;
    private String placeName;
    private List<HobieDTO> hobieList;
    private Status status;
    private List<PersonDTO> participants;
    private List<EventDTO> associatedEvents;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public List<HobieDTO> getHobieList() {
        return hobieList;
    }

    public void setHobieList(List<HobieDTO> hobieList) {
        this.hobieList = hobieList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<PersonDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PersonDTO> participants) {
        this.participants = participants;
    }

    public List<EventDTO> getAssociatedEvents() {
        return associatedEvents;
    }

    public void setAssociatedEvents(List<EventDTO> associatedEvents) {
        this.associatedEvents = associatedEvents;
    }
}
