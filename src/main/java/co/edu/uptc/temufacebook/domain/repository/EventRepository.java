package co.edu.uptc.temufacebook.domain.repository;

import co.edu.uptc.temufacebook.domain.dto.Event;
import co.edu.uptc.temufacebook.domain.dto.Status;

public interface EventRepository {
    Event saveEvent(Event event);
    Event getById(int id);
    void changeStatus(int id, Status status);
    void deleteEvent(int id);
    Event updateEvent(Event event);

}
