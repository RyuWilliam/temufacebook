package co.edu.uptc.temufacebook.domain.service;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.domain.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        return eventRepository.saveEvent(eventDTO);
    }

    public EventDTO getEventById(int id) {
        return eventRepository.getById(id);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.getAll();
    }

    public void deleteEvent(int id) {
        eventRepository.deleteEvent(id);
    }

    public EventDTO updateEvent(EventDTO eventDTO) {
        return eventRepository.updateEvent(eventDTO);
    }

}
