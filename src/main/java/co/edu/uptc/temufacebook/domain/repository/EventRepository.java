package co.edu.uptc.temufacebook.domain.repository;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.persistence.Status;

import java.util.List;

public interface EventRepository {

    EventDTO saveEvent(EventDTO event);

    EventDTO getById(int id);

    List<EventDTO> getAll();

    void changeStatus(int id, Status status);

    void deleteEvent(int id);

    EventDTO updateEvent(EventDTO event);

    List<EventDTO> getByStatus(Status status);


}
